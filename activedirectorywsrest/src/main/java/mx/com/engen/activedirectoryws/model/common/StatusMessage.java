/**
 * 
 */
package mx.com.engen.activedirectoryws.model.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class StatusMessage {
  /**
   * The Success.
   */
  @Setter
  @Getter
  private boolean success;
  /**
   * The Http status.
   */
  @Setter
  @Getter
  private Integer httpStatus;
  /**
   * The Error code.
   */
  @Setter
  @Getter
  private String errorCode;
  /**
   * The Error message.
   */
  @Setter
  @Getter
  private String errorMessage;
  /**
   * The Message.
   */
  @Setter
  @Getter
  private String message;
  
  public StatusMessage(boolean success, Integer httpStatus, String errorCode, String errorMessage, String message) {
    this.success = success;
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.message = message;    
  }
}
