/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneral;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class GeneralResponse extends CommonResponse {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Member Of.
   */
  @Setter
  @Getter
  private AdGeneral general;

  public GeneralResponse(AdGeneral general, boolean success, Integer httpStatus, String errorCode,
      String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.general = general;
  }
}
