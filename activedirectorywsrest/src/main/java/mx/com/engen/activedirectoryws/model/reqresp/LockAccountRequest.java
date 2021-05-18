/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@EqualsAndHashCode(callSuper = false)
public class LockAccountRequest {
  /**
   * Is Enabled.
   */
  @Getter
  @Setter
  private boolean isLocked;
}
