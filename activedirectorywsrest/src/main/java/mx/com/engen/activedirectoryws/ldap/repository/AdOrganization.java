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
public class AdOrganization implements Serializable{
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
   * Title Attribute Name.
   */
  public static final String TITLE_ATTRIBUTE_NAME = "title";
  /**
   * Manager Attribute Name.
   */
  public static final String MANAGER_ATTRIBUTE_NAME = "manager";
  /**
   * Department Attribute Name.
   */
  public static final String DEPARTMENT_ATTRIBUTE_NAME = "department";
  /**
   * Department Attribute Name.
   */
  public static final String COMPANY_ATTRIBUTE_NAME = "company";
  /**
   * User Logon Name.
   */
  @Setter
  @Getter
  @Attribute(name = "sAMAccountName")
  private String userLogonName;
  /**
   * Title.
   */
  @Setter
  @Getter
  private String title;
  /**
   * Department.
   */
  @Setter
  @Getter
  private String department;
  /**
   * Company.
   */
  @Setter
  @Getter
  private String company;
  /**
   * Manager.
   */
  @Setter
  @Getter
  private String manager;
  /**
   * Direct Reports.
   */
  @Setter
  @Getter
  private List<String> directReports;
}
