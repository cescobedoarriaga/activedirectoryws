package mx.com.engen.activedirectoryws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.engen.activedirectoryws.ldap.LdapClient;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddress;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddressRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneral;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneralRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdMemberOf;
import mx.com.engen.activedirectoryws.ldap.repository.AdMemberOfRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganization;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganizationRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdTelephone;
import mx.com.engen.activedirectoryws.ldap.repository.AdTelephoneRepository;
import mx.com.engen.activedirectoryws.model.AdServiceResult;
import mx.com.engen.activedirectoryws.model.LdapClientResult;

@Service
public class AdUserServiceImpl implements AdUserService {
  /**
   * LDAP Client.
   */
  @Autowired
  private LdapClient ldapClient;

  /**
   * General Repository.
   */
  @Autowired
  private AdGeneralRepository generalRepository;
  /**
   * Organization Repository.
   */
  @Autowired
  private AdOrganizationRepository organizationRepository;
  /**
   * Address Repository.
   */
  @Autowired
  private AdAddressRepository addressRepository;
  /**
   * Telephone Repository.
   */
  @Autowired
  private AdTelephoneRepository telephoneRepository;

  /**
   * Group Repository.
   */
  @Autowired
  private AdMemberOfRepository memberOfRepository;

  public AdServiceResult createUser(CreateUserIn createUserIn) {
    LdapClientResult result;

    result = ldapClient.create(createUserIn.getAdUser());

    if (result.isSuccess()) {
      return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    } else {
      return new AdServiceResult(LdapClientResult.ERROR_FLAG, result.getMessage(), result.getErrorCode(),
          result.getErrorMesage());
    }
  }

  public AdServiceResult changeUserToDnDelete(String userLogonName) {
    LdapClientResult result;

    result = ldapClient.changeUserToDnDelete(userLogonName);

    if (result.isSuccess()) {
      return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    } else {
      return new AdServiceResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, result.getErrorCode(),
          result.getErrorMesage());
    }
  }

  public AdMemberOf getMemberOfByUserLogonName(String userLogonName) {
    AdMemberOf memberOf;

    memberOf = memberOfRepository.findByuserLogonName(userLogonName);

    return memberOf;
  }

  public AdGeneral getGeneralByUserLogonName(String userLogonName) {
    AdGeneral general = generalRepository.findByGeneralUserLogonName(userLogonName);

    if (general == null) {
      return null;
    }

    return general;
  }

  public AdOrganization getOrganizationByUserLogonName(String userLogonName) {
    AdOrganization organization = organizationRepository.findByuserLogonName(userLogonName);

    if (organization == null) {
      return null;
    }

    return organization;
  }

  public AdAddress getAddressByUserLogonName(String userLogonName) {
    AdAddress address = addressRepository.findByAddressUserLogonName(userLogonName);

    if (address == null) {
      return null;
    }

    return address;
  }

  public AdTelephone getTelephoneByUserLogonName(String userLogonName) {
    AdTelephone telephone = telephoneRepository.findByuserLogonName(userLogonName);

    if (telephone == null) {
      return null;
    }

    return telephone;
  }

  public AdServiceResult updateUserManager(String userLogonName, String managerLogonName) {
    LdapClientResult result;

    result = ldapClient.updateUserManager(userLogonName, managerLogonName);

    return new AdServiceResult(result.isSuccess(), result.getMessage(), result.getErrorCode(), result.getErrorMesage());
  }

  public AdServiceResult removeUserManager(String managerLogonName) {
    LdapClientResult result;

    result = ldapClient.removeUserManager(managerLogonName);

    if (result.isSuccess()) {
      return new AdServiceResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    } else {
      return new AdServiceResult(result.isSuccess(), result.getMessage(), result.getErrorCode(),
          result.getErrorMesage());
    }
  }

}
