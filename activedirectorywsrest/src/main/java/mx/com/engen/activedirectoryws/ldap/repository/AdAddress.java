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
public class AdAddress implements Serializable{
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
   * Country  Attribute Name.
   */
  public static final String C_NAME_ATTRIBUTE_NAME = "c";
  /**
   * City Attribute Name.
   */
  public static final String L_NAME_ATTRIBUTE_NAME = "l";
  /**
   * State Attribute Name.
   */
  public static final String ST_NAME_ATTRIBUTE_NAME = "st";
  /**
   * Postal Code Attribute Name.
   */
  public static final String POSTAL_CODE_NAME_ATTRIBUTE_NAME = "postalCode";
  /**
   * Street Attribute Name.
   */
  public static final String STREET_ADDRESS_NAME_ATTRIBUTE_NAME = "streetAddress";
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String addressUserLogonName;
  /**
   * Street.
   */
  @Setter
  @Getter
  @Attribute(name = "streetAddress")
  private String street;
  /**
   * City.
   */
  @Setter
  @Getter
  @Attribute(name = "l")
  private String city;
  /**
   * State.
   */
  @Setter
  @Getter
  @Attribute(name = "st")
  private String state;
  /**
   * Postal Code.
   */
  @Setter
  @Getter
  @Attribute(name = "st")
  private String postalCode;
  /**
   * Country.
   */
  @Setter
  @Getter
  @Attribute(name = "c")
  private String country;
}
