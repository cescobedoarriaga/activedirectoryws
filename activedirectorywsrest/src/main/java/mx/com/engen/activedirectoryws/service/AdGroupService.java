/**
 * 
 */
package mx.com.engen.activedirectoryws.service;

import java.util.List;

import mx.com.engen.activedirectoryws.ldap.repository.AdGroup;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
/**
 * @author Carlos Escobedo Arriaga
 *
 */
public interface AdGroupService {
  List<AdGroup> getAllGroups();
  AdServiceResult addUserToGroup(String userLogonName, String groupname);
  AdServiceResult removeUserFromGroup(String userLogonName, String groupname);
}
