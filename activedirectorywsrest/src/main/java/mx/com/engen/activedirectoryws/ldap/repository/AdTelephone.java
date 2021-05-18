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
public class AdTelephone implements Serializable{
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
   * Department Attribute Name.
   */
  public static final String IP_PHONE_ATTRIBUTE_NAME = "ipPhone";
  /**
   * Department Attribute Name.
   */
  public static final String MOBILE_ATTRIBUTE_NAME = "mobile";
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String userLogonName;
  /**
   * Mobile.
   */
  @Setter
  @Getter
  private String mobile;
  /**
   * ipPhone.
   */
  @Setter
  @Getter
  private String ipPhone;
}
