/**
 * 
 */
package mx.com.engen.activedirectoryws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.engen.activedirectoryws.ldap.LdapClient;
import mx.com.engen.activedirectoryws.ldap.repository.AdGroup;
import mx.com.engen.activedirectoryws.ldap.repository.AdGroupRepository;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.LdapClientResult;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Service
public class AdGroupServiceImpl implements AdGroupService {
  /**
   * LDAP Client.
   */
  @Autowired
  private LdapClient ldapClient;
  /**
   * Group Repository.
   */
  @Autowired
  private AdGroupRepository groupRepository;

  public List<AdGroup> getAllGroups() {

    return groupRepository.findAll();
  }

  public AdServiceResult addUserToGroup(String userLogonName, String groupname) {
    LdapClientResult result;

    result = ldapClient.addMemberToGroup(userLogonName, groupname);

    if (result.isSuccess()) {
      return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    } else {
      return new AdServiceResult(result.isSuccess(), result.getMessage(), result.getErrorCode(),
          result.getErrorMesage());
    }
  }

  public AdServiceResult removeUserFromGroup(String groupname, String userFullName) {
    LdapClientResult result;

    result = ldapClient.removeMemberFromGroup(groupname, userFullName);

    if (result.isSuccess()) {
      return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    } else {
      return new AdServiceResult(result.isSuccess(), result.getMessage(), result.getErrorCode(),
          result.getErrorMesage());
    }
  }
}
