/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Entry(base = "ou=users", objectClasses = { "user" })
public class AdAccount implements Serializable{
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * ID.
   */
  @Id
  private Name id;
  /**
   * sAMAccountName Attribute Name.
   */
  public static final String SAM_ACCOUNT_NAME_ATTRIBUTE_NAME = "sAMAccountName";
  /**
   * User Principal Name Attribute Name.
   */
  public static final String USER_PRINCIPAL_NAME_ATTRIBUTE_NAME = "userPrincipalName";
  /**
   * User Account Control Attribute Name.
   */
  public static final String USER_ACCOUNT_CONTROL_ATTRIBUTE_NAME = "userAccountControl";
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String userLogonName;
  /**
   * Full Name.
   */
  @Setter
  @Getter
  @Attribute(name = "cn")
  private String fullName;
  /**
   * User Principal Name.
   */
  @Setter
  @Getter
  private String userPrincipalName;
  /**
   * User Account Control.
   */
  @Setter
  @Getter
  private int userAccountControl;
  /**
   * Distinguished Name.
   */
  @Setter
  @Getter
  private String distinguishedName;

}
