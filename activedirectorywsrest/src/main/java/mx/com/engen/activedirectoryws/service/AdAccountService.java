/**
 * 
 */
package mx.com.engen.activedirectoryws.service;

import mx.com.engen.activedirectoryws.model.AccountListResult;
import mx.com.engen.activedirectoryws.model.AccountResult;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.service.AdUserService.LockAccountIn;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public interface AdAccountService {
  AccountListResult getAllAccounts();
  AccountResult getAccountByUserLogonName(String userLogonName); 
  AdServiceResult updateAccount(LockAccountIn lockAccountIn);
}
