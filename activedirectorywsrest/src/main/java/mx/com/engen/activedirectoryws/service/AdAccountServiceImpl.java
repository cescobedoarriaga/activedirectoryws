/**
 * 
 */
package mx.com.engen.activedirectoryws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.engen.activedirectoryws.ldap.UserAccountControlValues;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccountRepository;
import mx.com.engen.activedirectoryws.model.AccountListResult;
import mx.com.engen.activedirectoryws.model.AccountResult;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.LdapClientResult;
import mx.com.engen.activedirectoryws.service.AdUserService.LockAccountIn;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Service
@Slf4j
public class AdAccountServiceImpl implements AdAccountService {
  /**
   * Account Repository.
   */
  @Autowired
  private AdAccountRepository accountRepository;

  public AccountListResult getAllAccounts() {
    List<AdAccount> accountList;

    accountList = accountRepository.findAll();

    return new AccountListResult(accountList, LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null,
        null);
  }

  public AccountResult getAccountByUserLogonName(String userLogonName) {
    AdAccount account;
    log.info("Service: " + userLogonName);
    account = accountRepository.findByuserLogonName(userLogonName);
    
    if (account == null) {
      return new AccountResult(null, LdapClientResult.ERROR_FLAG, null, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }
    log.info("DN: " + account.getDistinguishedName());
    return new AccountResult(account, LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }
  
  public AdServiceResult updateAccount(LockAccountIn lockAccountIn) {

    AdAccount account = accountRepository.findByuserLogonName(lockAccountIn.getUserLogonName());

    if (account == null) {

      return new AdServiceResult(LdapClientResult.ERROR_FLAG, null, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + lockAccountIn.getUserLogonName());
    }

    if (lockAccountIn.isLocked()) {
      account.setUserAccountControl(UserAccountControlValues.ACCOUNTDISABLE);
    } else {
      account.setUserAccountControl(UserAccountControlValues.NORMAL_ACCOUNT);
    }

    accountRepository.save(account);

    return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }
}
