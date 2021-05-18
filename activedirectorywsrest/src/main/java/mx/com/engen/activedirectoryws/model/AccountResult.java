/**
 * 
 */
package mx.com.engen.activedirectoryws.model;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;
import mx.com.engen.activedirectoryws.model.common.CommonResult;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class AccountResult extends CommonResult{
  /**
   * Account.
   */
  @Setter
  @Getter
  private AdAccount account;
  
  public AccountResult(AdAccount account, boolean success, String message, String errorCode, String errorMessage) {
    super(success, message, errorCode, errorMessage);
    this.account = account;
  }
}
