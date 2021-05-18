/**
 * 
 */
package mx.com.engen.activedirectoryws.model;

import mx.com.engen.activedirectoryws.model.common.CommonResult;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class AdServiceResult extends CommonResult{

  public AdServiceResult(boolean success, String message, String errorCode, String errorMessage) {
    super(success, message, errorCode, errorMessage);
  }
}
