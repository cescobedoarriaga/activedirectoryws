/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class LockAccountResponse extends CommonResponse {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  private String userLogonName;
  /**
   * Is Locked.
   */
  @Setter
  @Getter
  private boolean isLocked;

  public LockAccountResponse(String userLogonName, boolean locked, boolean success, Integer httpStatus,
      String errorCode, String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.userLogonName = userLogonName;
    this.isLocked = locked;
  }
}
