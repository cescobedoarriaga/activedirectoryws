/**
 * 
 */
package mx.com.engen.activedirectoryws.model.reqresp;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganization;
import mx.com.engen.activedirectoryws.model.common.CommonResponse;

/**
 * @author 503001131
 *
 */
public class OrganizationResponse extends CommonResponse {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Member Of.
   */
  @Setter
  @Getter
  private AdOrganization organization;

  public OrganizationResponse(AdOrganization organization, boolean success, Integer httpStatus, String errorCode,
      String errorMessage, String message) {
    super(success, httpStatus, errorCode, errorMessage, message);
    this.organization = organization;
  }
}
