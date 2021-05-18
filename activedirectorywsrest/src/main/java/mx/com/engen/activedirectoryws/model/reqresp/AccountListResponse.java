/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga.
 *
 */
public class AccountListResponse extends CommonResponse {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Account.
   */
  @Setter
  @Getter
  private List<AdAccount> accountList;

  public AccountListResponse(List<AdAccount> accountList, boolean success, Integer httpStatus, String errorCode,
      String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.accountList = new ArrayList<>(accountList);
  }
}
