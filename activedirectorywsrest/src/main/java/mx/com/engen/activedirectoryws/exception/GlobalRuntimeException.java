/**
 * 
 */
package mx.com.engen.activedirectoryws.exception;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class GlobalRuntimeException extends RuntimeException {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   * @param t
   */
  public GlobalRuntimeException(Throwable t) {
    super(t);
  }
}

