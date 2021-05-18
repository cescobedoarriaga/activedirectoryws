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
public class AdGeneral implements Serializable{
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
   * Full Name Attribute Name.
   */
  public static final String CN_ATTRIBUTE_NAME = "cn";
  /**
   * Mail Attribute Name.
   */
  public static final String MAIL_ATTRIBUTE_NAME = "mail";
  /**
   * Given Name Attribute Name.
   */
  public static final String GIVEN_NAME_ATTRIBUTE_NAME = "givenName";
  /**
   * Surname Attribute Name.
   */
  public static final String SN_ATTRIBUTE_NAME = "sn";
  /**
   * Surname Attribute Name.
   */
  public static final String DESCRIPTION_ATTRIBUTE_NAME = "description";
  /**
   * Office Attribute Name.
   */
  public static final String PHYSICAL_DELIVERY_OFFICE_NAME_ATTRIBUTE_NAME = "physicalDeliveryOfficeName";
  /**
   * Telephone Number Attribute Name.
   */
  public static final String TELEPHONE_NUMBER_ATTRIBUTE_NAME = "telephoneNumber";
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String generalUserLogonName;
  /**
   * First Name.
   */
  @Setter
  @Getter
  @Attribute(name = "givenName")
  private String firstName;
  /**
   * Last Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sn")
  private String lastName;
  /**
   * Full Name.
   */
  @Setter
  @Getter
  @Attribute(name = "cn")
  private String fullName;
  /**
   * Display Name.
   */
  @Setter
  @Getter
  private String displayName;
  /**
   * Description.
   */
  @Setter
  @Getter
  private String description;
  /**
   * Office.
   */
  @Setter
  @Getter
  @Attribute(name = "physicalDeliveryOfficeName")
  private String office;
  /**
   * Telephone Number.
   */
  @Setter
  @Getter
  private String telephoneNumber;
  /**
   * Mail.
   */
  @Setter
  @Getter
  private String mail;  
}
