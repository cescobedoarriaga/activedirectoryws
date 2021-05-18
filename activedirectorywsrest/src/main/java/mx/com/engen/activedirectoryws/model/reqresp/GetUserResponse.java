/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.EqualsAndHashCode;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@EqualsAndHashCode(callSuper = false)
public class GetUserResponse extends CommonResponse{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public GetUserResponse(boolean success, Integer httpStatus, String errorCode, String errorMessage,
      String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
  }
}
