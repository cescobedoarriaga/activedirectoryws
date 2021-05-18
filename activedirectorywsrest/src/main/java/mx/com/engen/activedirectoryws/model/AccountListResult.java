/**
 * 
 */
package mx.com.engen.activedirectoryws.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;
import mx.com.engen.activedirectoryws.model.common.CommonResult;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class AccountListResult extends CommonResult {
  /**
   * Account.
   */
  @Setter
  @Getter
  private List<AdAccount> accountList;

  public AccountListResult(List<AdAccount> accountList, boolean success, String message, String errorCode,
      String errorMessage) {
    super(success, message, errorCode, errorMessage);
    this.accountList = new ArrayList<>(accountList);
  }
}
