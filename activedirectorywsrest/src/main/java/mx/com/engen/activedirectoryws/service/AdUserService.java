package mx.com.engen.activedirectoryws.service;

import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddress;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneral;
import mx.com.engen.activedirectoryws.ldap.repository.AdMemberOf;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganization;
import mx.com.engen.activedirectoryws.ldap.repository.AdTelephone;
import mx.com.engen.activedirectoryws.ldap.repository.AdUser;
import mx.com.engen.activedirectoryws.model.AdServiceResult;

public interface AdUserService {
  
  AdServiceResult createUser(CreateUserIn createUserIn);  
  AdServiceResult changeUserToDnDelete(String userLogonName);
  AdMemberOf getMemberOfByUserLogonName(String userLogonName);  
  AdGeneral getGeneralByUserLogonName(String userLogonName);
  AdOrganization getOrganizationByUserLogonName(String userLogonName);
  AdAddress getAddressByUserLogonName(String userLogonName);
  AdTelephone getTelephoneByUserLogonName(String userLogonName);  
  AdServiceResult updateUserManager(String userLogonName, String managerLogonName);  
  AdServiceResult removeUserManager(String managerLogonName);
  

  /**
   * @return ldapAttributes
   */
  @Getter
  @Setter
  class CreateUserIn {
    /**
     * Active Directory User.
     */
    @Getter
    @Setter
    private AdUser adUser;
  }
  @Getter
  @Setter
  class LockAccountIn {
    /**
     * User Logon Name.
     */
    @Getter
    @Setter
    private String userLogonName;
    /**
     * Is Locked.
     */
    @Getter
    @Setter
    private boolean isLocked;
  }
  @Getter
  @Setter
  class ModifyOrganizationOut {
    /**
     * Result.
     */
    @Setter
    @Getter
    private String result;
  }

}
