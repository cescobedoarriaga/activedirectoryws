/**
 * 
 */
package mx.com.engen.activedirectoryws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.engen.activedirectoryws.model.AccountListResult;
import mx.com.engen.activedirectoryws.model.AccountResult;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.reqresp.AccountListResponse;
import mx.com.engen.activedirectoryws.model.reqresp.AccountResponse;
import mx.com.engen.activedirectoryws.model.reqresp.LockAccountRequest;
import mx.com.engen.activedirectoryws.model.reqresp.LockAccountResponse;
import mx.com.engen.activedirectoryws.service.AdAccountService;
import mx.com.engen.activedirectoryws.service.AdUserService.LockAccountIn;
import mx.com.engen.activedirectoryws.util.HttpResponseValues;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@OpenAPIDefinition(info = @Info(title = "Active Directory API"), tags = { @Tag(name = "Users"), @Tag(name = "Groups"),
    @Tag(name = "Accounts") })
@RestController
public class AdAccountController {
  /**
   * ActiveDirectory Service.
   */
  @Autowired
  private AdAccountService accountService;

  @Tag(name = "Accounts")
  @Operation(summary = "Get account information for all of the users.")
  @GetMapping(path = "/accounts")
  public AccountListResponse getAllAccounts() {
    AccountListResult result;

    result = accountService.getAllAccounts();

    return new AccountListResponse(result.getAccountList(), HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS,
        null, null, null);
  }

  @Tag(name = "Users")
  @Operation(summary = "Get account information by user logon name.")
  @GetMapping(path = "/users/{userLogonName}/accounts")
  public AccountResponse searchAccountByUserLogonName(@PathVariable String userLogonName) {
    AccountResult result;

    result = accountService.getAccountByUserLogonName(userLogonName);

    if (result.isSuccess()) {
      return new AccountResponse(result.getAccount(), HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null,
          null, HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new AccountResponse(null, HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE,
          result.getErrorCode(), result.getErrorMesage(), null);
    }
  }

  @Tag(name = "Users")
  @PutMapping(path = "/users/{userLogonName}/accounts/")
  @Operation(summary = "Lock or unlock an account.")
  public LockAccountResponse modifyAccount(@PathVariable String userLogonName,
      @RequestBody LockAccountRequest request) {
    LockAccountIn lockAccountIn;
    AdServiceResult result;

    lockAccountIn = new LockAccountIn();

    lockAccountIn.setUserLogonName(userLogonName);
    lockAccountIn.setLocked(request.isLocked());

    result = accountService.updateAccount(lockAccountIn);

    if (result.isSuccess()) {
      return new LockAccountResponse(lockAccountIn.getUserLogonName(), lockAccountIn.isLocked(),
          HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null, HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new LockAccountResponse(null, false, HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE,
          result.getErrorCode(), result.getErrorMesage(), null);
    }
  }
}
