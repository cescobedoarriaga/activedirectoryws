/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;

import mx.com.engen.activedirectoryws.ldap.repository.AdTelephone;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class TelephoneResponse extends CommonResponse{
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Telephone.
   */
  @Setter
  @Getter
  private AdTelephone telephone;

  public TelephoneResponse(AdTelephone telephone, boolean success, Integer httpStatus, String errorCode,
      String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.telephone = telephone;
  }
}
