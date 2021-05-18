/**
 * 
 */
package mx.com.engen.activedirectoryws.model;

import mx.com.engen.activedirectoryws.model.common.CommonResult;

/**
 * @author Carlos Escobedo Arriaga.
 *
 */
public class LdapClientResult extends CommonResult {
  /**
   * Success Flag.
   */
  public static final boolean SUCCESS_FLAG = true;
  /**
   * Success Message.
   */
  public static final String SUCCESS_MESSAGE = "Success";
  /**
   * Success Message.
   */
  public static final String WARNING_MESSAGE = "Warning";
  /**
   * Error Flag.
   */
  public static final boolean ERROR_FLAG = false;
  /**
   * Error Message.
   */
  public static final String ERROR_MESSAGE = "Error";
  /**
   * User Not Found Message.
   */
  public static final String USER_NOT_FOUND_MESSAGE = "User Not Found";
  /**
   * Group Not Found Message.
   */
  public static final String GROUP_NOT_FOUND_MESSAGE = "Group Not Found";

  public LdapClientResult(boolean success, String message, String errorCode, String errorMessage) {
    super(success, message, errorCode, errorMessage);
  }
}
