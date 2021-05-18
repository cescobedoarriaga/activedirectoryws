/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import java.io.Serializable;
import java.util.List;

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
public class AdAttributes implements Serializable{
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
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String userLogonName;
  /**
   * Distinguished Name.
   */
  @Setter
  @Getter
  private String distinguishedName;
  /**
   * Full Name.
   */
  @Setter
  @Getter
  @Attribute(name = "cn")
  private String fullName;
  /**
   * Object Class.
   */
  @Setter
  @Getter
  @Attribute(name = "objectClass")
  private List<String> objectClasses;
}
