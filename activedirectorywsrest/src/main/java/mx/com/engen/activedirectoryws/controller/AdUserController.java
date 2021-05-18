package mx.com.engen.activedirectoryws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddress;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneral;
import mx.com.engen.activedirectoryws.ldap.repository.AdMemberOf;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganization;
import mx.com.engen.activedirectoryws.ldap.repository.AdTelephone;
import mx.com.engen.activedirectoryws.ldap.repository.AdUser;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;
import mx.com.engen.activedirectoryws.model.reqresp.AddressResponse;
import mx.com.engen.activedirectoryws.model.reqresp.CreateUserRequest;
import mx.com.engen.activedirectoryws.model.reqresp.GeneralResponse;
import mx.com.engen.activedirectoryws.model.reqresp.MemberOfResponse;
import mx.com.engen.activedirectoryws.model.reqresp.OrganizationResponse;
import mx.com.engen.activedirectoryws.model.reqresp.TelephoneResponse;
import mx.com.engen.activedirectoryws.service.AdUserService;
import mx.com.engen.activedirectoryws.service.AdUserService.CreateUserIn;
import mx.com.engen.activedirectoryws.util.HttpResponseValues;

@OpenAPIDefinition(info = @Info(title = "Active Directory API"), tags = { @Tag(name = "Users"), @Tag(name = "Groups") })
@RestController
public class AdUserController {
  /**
   * ActiveDirectory Service.
   */
  @Autowired
  private AdUserService userService;

  @Operation(summary = "Create a user.")
  @Tag(name = "Users")
  @PostMapping(path = "/users")
  public CommonResponse createUser(@Valid @RequestBody CreateUserRequest request) {
    AdUser adUser;
    CreateUserIn createUserIn;
    AdServiceResult result;

    adUser = request.getUser();

    createUserIn = new CreateUserIn();
    createUserIn.setAdUser(adUser);

    result = userService.createUser(createUserIn);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), result.getMessage());
    }
  }

  @Tag(name = "Users")
  @Operation(summary = "Get the groups that the user is a member of")
  @GetMapping(path = "/users/{userLogonName}/memberOf")
  public MemberOfResponse searchMemberOfByUserLogonName(@PathVariable String userLogonName) {
    AdMemberOf memberOf;

    memberOf = userService.getMemberOfByUserLogonName(userLogonName);

    return new MemberOfResponse(memberOf, HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
        HttpResponseValues.SUCCESS_MESSAGE);
  }

  @Tag(name = "Users")
  @Operation(summary = "Get general information by user logon name.")
  @GetMapping(path = "/users/{userLogonName}/generals")
  public GeneralResponse searchGeneralByUserLogonName(@PathVariable String userLogonName) {
    AdGeneral general;

    general = userService.getGeneralByUserLogonName(userLogonName);

    return new GeneralResponse(general, HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
        HttpResponseValues.SUCCESS_MESSAGE);
  }

  @Tag(name = "Users")
  @Operation(summary = "Get organization information by user logon name.")
  @GetMapping(path = "/users/{userLogonName}/organizations")
  public OrganizationResponse searchOrganizationByUserLogonName(@PathVariable String userLogonName) {
    AdOrganization organization;

    organization = userService.getOrganizationByUserLogonName(userLogonName);

    return new OrganizationResponse(organization, HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null,
        null, HttpResponseValues.SUCCESS_MESSAGE);
  }

  @Tag(name = "Users")
  @GetMapping(path = "/users/{userLogonName}/addresses")
  @Operation(summary = "Get address information by user logon name.")
  public AddressResponse searchAddressByUserLogonName(@PathVariable String userLogonName) {
    AdAddress address;

    address = userService.getAddressByUserLogonName(userLogonName);

    return new AddressResponse(address, HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
        HttpResponseValues.SUCCESS_MESSAGE);
  }

  @Tag(name = "Users")
  @GetMapping(path = "/users/{userLogonName}/telephones")
  @Operation(summary = "Get telephone information by user logon name.")
  public TelephoneResponse searchTelephoneByUserLogonName(@PathVariable String userLogonName) {
    AdTelephone telephone;

    telephone = userService.getTelephoneByUserLogonName(userLogonName);

    return new TelephoneResponse(telephone, HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
        HttpResponseValues.SUCCESS_MESSAGE);
  }

  @Tag(name = "Users")
  @DeleteMapping(path = "/users/{userLogonName}")
  @Operation(summary = "Delete user.")
  public CommonResponse changeUserToDnDelete(@PathVariable String userLogonName) {
    AdServiceResult result;

    result = userService.changeUserToDnDelete(userLogonName);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), HttpResponseValues.ERROR_MESSAGE);
    }
  }

  @Tag(name = "Users")
  @PutMapping(path = "/users/{userLogonName}/managers/{managerLogonName}")
  @Operation(summary = "Update a user's manager.")
  public CommonResponse updateUserManager(@PathVariable String userLogonName, @PathVariable String managerLogonName) {
    AdServiceResult result;

    result = userService.updateUserManager(userLogonName, managerLogonName);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), HttpResponseValues.ERROR_MESSAGE);
    }

  }

  @Tag(name = "Users")
  @DeleteMapping(path = "/users/{userLogonName}/organizations/managers")
  @Operation(summary = "Remove a user's manager.")
  public CommonResponse removeUserManager(@PathVariable String userLogonName) {
    AdServiceResult result;

    result = userService.removeUserManager(userLogonName);

    if (result.isSuccess()) {
      return new CommonResponse(HttpResponseValues.SUCCESS, HttpResponseValues.HTTP_SUCCESS, null, null,
          HttpResponseValues.SUCCESS_MESSAGE);
    } else {
      return new CommonResponse(HttpResponseValues.ERROR, HttpResponseValues.HTTP_APP_FAILURE, result.getErrorCode(),
          result.getErrorMesage(), HttpResponseValues.ERROR_MESSAGE);
    }
  }
}
