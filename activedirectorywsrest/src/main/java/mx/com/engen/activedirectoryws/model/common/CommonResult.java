/**
 * 
 */
package mx.com.engen.activedirectoryws.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 503001131
 *
 */
public class CommonResult {
  /**
   * Success.
   */
  @Setter
  @Getter
  private boolean success;
  /**
   * Message.
   */
  @Setter
  @Getter
  private String message;
  /**
   * Error Code.
   */
  @Setter
  @Getter
  private String errorCode;
  /**
   * Error Message.
   */
  @Setter
  @Getter
  private String errorMesage;
  
  public CommonResult(boolean success, String message, String errorCode, String errorMessage) {
    this.success = success;
    this.message = message;
    this.errorCode = errorCode;
    this.errorMesage = errorMessage;
  }
}
