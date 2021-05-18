/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Entry(base = "ou=users", objectClasses = { "user" })
public class AdUser{
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String userLogonName;
  /**
   * User Type.
   */
  @Setter
  @Getter
  private String userType;
  /**
   * DN.
   */
  @Setter
  @Getter
  private Name dn;
  /**
   * Account.
   */
  @Setter
  @Getter
  private AdAccount account;
  /**
   * Account.
   */
  @Setter
  @Getter
  private AdMemberOf groups;
  /**
   * General.
   */
  @Setter
  @Getter
  private AdGeneral general;
  /**
   * Organization.
   */
  @Setter
  @Getter
  private AdOrganization organization;
  /**
   * Address.
   */
  @Setter
  @Getter
  private AdAddress address;
  /**
   * Telephone.
   */
  @Setter
  @Getter
  private AdTelephone telephone;
  
  public String buildCn() {
    return this.general.getFirstName() + " " + this.general.getLastName();
  }
}
