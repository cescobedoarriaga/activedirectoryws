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
@Entry(objectClasses = {"top", "group"}, base = "ou=Groups")
public class AdGroup implements Serializable{
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
   * CN.
   */
  @Setter
  @Getter
  @Attribute(name = "cn")
  private String groupName;
  /**
   * Distinguished Name.
   */
  @Setter
  @Getter
  private String distinguishedName;
  /**
   * Members.
   */
  @Setter
  @Getter
  @Attribute(name = "member")
  private List<String> members;
  /**
   * Object Classes.
   */
  @Setter
  @Getter
  @Attribute(name = "objectClass")
  private List<String> objectClasses;
  
  public void addMember(String distinguishedName) { 
    
    this.members.add(distinguishedName);
  }
  
}
