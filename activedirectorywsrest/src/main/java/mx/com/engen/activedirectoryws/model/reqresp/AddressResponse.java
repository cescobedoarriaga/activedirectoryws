/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddress;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class AddressResponse extends CommonResponse {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Member Of.
   */
  @Setter
  @Getter
  private AdAddress address;

  public AddressResponse(AdAddress address, boolean success, Integer httpStatus, String errorCode,
      String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.address = address;
  }
}
