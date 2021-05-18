/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class AccountResponse extends CommonResponse {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Account.
   */
  @Setter
  @Getter
  private AdAccount account;

  public AccountResponse(AdAccount account, boolean success, Integer httpStatus, String errorCode, String errorMessage,
      String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.account = account;
  }
}
