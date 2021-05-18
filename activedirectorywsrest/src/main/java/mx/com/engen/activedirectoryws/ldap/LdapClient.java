/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;

import lombok.extern.slf4j.Slf4j;
import mx.com.engen.activedirectoryws.ldap.repository.AdAccount;
import mx.com.engen.activedirectoryws.ldap.repository.AdAddress;
import mx.com.engen.activedirectoryws.ldap.repository.AdAttributes;
import mx.com.engen.activedirectoryws.ldap.repository.AdAttributesRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdGeneral;
import mx.com.engen.activedirectoryws.ldap.repository.AdGroup;
import mx.com.engen.activedirectoryws.ldap.repository.AdGroupRepository;
import mx.com.engen.activedirectoryws.ldap.repository.AdOrganization;
import mx.com.engen.activedirectoryws.ldap.repository.AdTelephone;
import mx.com.engen.activedirectoryws.ldap.repository.AdUser;
import mx.com.engen.activedirectoryws.model.LdapClientResult;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Slf4j
public class LdapClient {
  /**
   * LDAP Template.
   */
  @Autowired
  private LdapTemplate ldapTemplate;
  /**
   * Environment.
   */
  @Autowired
  private Environment environment;
  /**
   * Group Repository.
   */
  @Autowired
  private AdAttributesRepository attributesRepository;
  /**
   * Group Repository.
   */
  @Autowired
  private AdGroupRepository groupRepository;

  public LdapClientResult create(final AdUser user) {
    StringBuilder result;
    int errorsCount;

    result = new StringBuilder("User Created");
    errorsCount = 0;

    Name userDn = buildUserDnName(user.getUserType(), user.buildCn());

    user.setDn(userDn);

    DirContextAdapter context = new DirContextAdapter(userDn);

    context.setAttributeValues("objectclass",
        new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });

    context.setAttributeValue(AdAccount.USER_PRINCIPAL_NAME_ATTRIBUTE_NAME, user.getAccount().getUserPrincipalName());
    context.setAttributeValue(AdAccount.SAM_ACCOUNT_NAME_ATTRIBUTE_NAME, user.getAccount().getUserLogonName());
    context.setAttributeValue(AdAccount.USER_ACCOUNT_CONTROL_ATTRIBUTE_NAME,
        Integer.toString(UserAccountControlValues.NORMAL_ACCOUNT));
    context.setAttributeValue(AdGeneral.CN_ATTRIBUTE_NAME, user.buildCn());
    context.setAttributeValue(AdGeneral.MAIL_ATTRIBUTE_NAME, user.getGeneral().getMail());
    context.setAttributeValue(AdGeneral.GIVEN_NAME_ATTRIBUTE_NAME, user.getGeneral().getFirstName());
    context.setAttributeValue(AdGeneral.SN_ATTRIBUTE_NAME, user.getGeneral().getLastName());
    context.setAttributeValue(AdGeneral.DESCRIPTION_ATTRIBUTE_NAME, user.getGeneral().getDescription());
    context.setAttributeValue(AdGeneral.PHYSICAL_DELIVERY_OFFICE_NAME_ATTRIBUTE_NAME, user.getGeneral().getOffice());
    context.setAttributeValue(AdGeneral.TELEPHONE_NUMBER_ATTRIBUTE_NAME, user.getGeneral().getTelephoneNumber());

    context.setAttributeValue(AdOrganization.TITLE_ATTRIBUTE_NAME, user.getOrganization().getTitle());
    context.setAttributeValue(AdOrganization.DEPARTMENT_ATTRIBUTE_NAME, user.getOrganization().getDepartment());
    context.setAttributeValue(AdOrganization.COMPANY_ATTRIBUTE_NAME, user.getOrganization().getCompany());

    context.setAttributeValue(AdAddress.POSTAL_CODE_NAME_ATTRIBUTE_NAME, user.getAddress().getPostalCode());
    context.setAttributeValue(AdAddress.C_NAME_ATTRIBUTE_NAME, user.getAddress().getCountry());
    context.setAttributeValue(AdAddress.L_NAME_ATTRIBUTE_NAME, user.getAddress().getCity());
    context.setAttributeValue(AdAddress.ST_NAME_ATTRIBUTE_NAME, user.getAddress().getState());
    context.setAttributeValue(AdAddress.STREET_ADDRESS_NAME_ATTRIBUTE_NAME, user.getAddress().getStreet());

    context.setAttributeValue(AdTelephone.IP_PHONE_ATTRIBUTE_NAME, user.getTelephone().getIpPhone());
    context.setAttributeValue(AdTelephone.MOBILE_ATTRIBUTE_NAME, user.getTelephone().getMobile());

    AdAttributes adAttributesManager;
    adAttributesManager = attributesRepository.findByuserLogonName(user.getOrganization().getManager());

    if (adAttributesManager == null) {
      result.append("|" + LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + user.getOrganization().getManager() + ".");
      errorsCount++;
    } else {
      context.setAttributeValue(AdOrganization.MANAGER_ATTRIBUTE_NAME, adAttributesManager.getDistinguishedName());
    }

    ldapTemplate.bind(context);

    for (String group : user.getGroups().getMemberOf()) {
      LdapClientResult addGroupResult;
      addGroupResult = addMemberToGroup(user.getAccount().getUserLogonName(), group);
      if (!addGroupResult.isSuccess()) {
        String errorCode;
        if (addGroupResult.getErrorCode() == null) {
          errorCode = "-";
        } else {
          errorCode = addGroupResult.getErrorCode();
        }
        result.append("|" + "Error Code: " + errorCode + " Error Messsage" + addGroupResult.getErrorMesage());
        errorsCount++;
      }
    }

    if (errorsCount > 0) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.WARNING_MESSAGE, null,
          result.toString());
    } else {
      return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
    }

  }

  public LdapClientResult changeUserToDnDelete(String userLogonName) {
    AdAttributes adAttributes;

    adAttributes = attributesRepository.findByuserLogonName(userLogonName);

    if (adAttributes == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    final String url = environment.getProperty("ldap.url");
    final String conntype = environment.getProperty("ldap.connectionType");
    final String adminDn = environment.getProperty("ldap.userDn");
    final String password = environment.getProperty("ldap.password");
    final String contextFactory = environment.getProperty("ldap.contextFactory");
    final String deletedUsersDn = environment.getProperty("ldap.deletedUsersDn");

    Properties env = new Properties();
    DirContext dctx = null;

    env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
    env.put(Context.PROVIDER_URL, url);
    env.put(Context.SECURITY_AUTHENTICATION, conntype);
    env.put(Context.SECURITY_PRINCIPAL, adminDn);
    env.put(Context.SECURITY_CREDENTIALS, password);

    try {
      dctx = new InitialDirContext(env);
      dctx.rename(adAttributes.getDistinguishedName(), "CN=" + adAttributes.getFullName() + "," + deletedUsersDn);
    } catch (NamingException e) {
      log.error("Excepción al crear el contexto: ", e);
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, null, null, e.getMessage());
    } finally {
      if (null != dctx) {
        try {
          dctx.close();
        } catch (NamingException e) {
          log.error("Error closing LDAP context: ", e);
        }
      }
    }
    return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }

  public LdapClientResult addMemberToGroup(String userLogonName, String groupName) {
    AdAttributes adAttributes;

    adAttributes = attributesRepository.findByuserLogonName(userLogonName);

    if (adAttributes == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    AdGroup adGroup;
    adGroup = groupRepository.findByGroupName(groupName);

    if (adGroup == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.GROUP_NOT_FOUND_MESSAGE + ". " + groupName);
    }

    adGroup.addMember(adAttributes.getDistinguishedName());

    Name groupDn;
    groupDn = buildGroupDnName(groupName);

    DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
    ctx.addAttributeValue("member", adAttributes.getDistinguishedName());

    ldapTemplate.modifyAttributes(ctx);

    return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }

  public LdapClientResult updateUserManager(String userLogonName, String managerLogonName) {
    AdAttributes adAttributesUser;

    adAttributesUser = attributesRepository.findByuserLogonName(userLogonName);

    if (adAttributesUser == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    AdAttributes adAttributesManager;
    adAttributesManager = attributesRepository.findByuserLogonName(managerLogonName);

    if (adAttributesManager == null) {
      log.info("No se encontró al manager");
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    Attribute attr = new BasicAttribute(AdOrganization.MANAGER_ATTRIBUTE_NAME,
        adAttributesManager.getDistinguishedName());
    ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

    ldapTemplate.modifyAttributes(buildUserDnName("EMPLOYEES", adAttributesUser.getFullName()),
        new ModificationItem[] { item });

    return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }

  public LdapClientResult removeUserManager(String userLogonName) {
    AdAttributes adAttributesUser;

    adAttributesUser = attributesRepository.findByuserLogonName(userLogonName);

    if (adAttributesUser == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    Attribute attr = new BasicAttribute(AdOrganization.MANAGER_ATTRIBUTE_NAME, null);
    ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);

    ldapTemplate.modifyAttributes(buildUserDnName("EMPLOYEES", adAttributesUser.getFullName()),
        new ModificationItem[] { item });
    return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }

  public LdapClientResult removeMemberFromGroup(String userLogonName, String groupName) {
    AdAttributes adAttributes;

    adAttributes = attributesRepository.findByuserLogonName(userLogonName);
    if (adAttributes == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.USER_NOT_FOUND_MESSAGE + ". " + userLogonName);
    }

    AdGroup adGroup;
    adGroup = groupRepository.findByGroupName(groupName);
    if (adGroup == null) {
      return new LdapClientResult(LdapClientResult.ERROR_FLAG, LdapClientResult.ERROR_MESSAGE, null,
          LdapClientResult.GROUP_NOT_FOUND_MESSAGE + ". " + groupName);
    }

    Name groupDn;
    groupDn = buildGroupDnName(groupName);

    DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
    ctx.removeAttributeValue("member", adAttributes.getDistinguishedName());

    ldapTemplate.modifyAttributes(ctx);

    return new LdapClientResult(LdapClientResult.SUCCESS_FLAG, LdapClientResult.SUCCESS_MESSAGE, null, null);
  }

  public Name buildUserDnName(String userType, String userFullName) {
    String userTypeOu;
    if (userType.equals(LdapUserTypeEnum.EMPLOYEE.name())) {
      userTypeOu = "Employees";
    } else {
      userTypeOu = "Contractors";
    }
    log.info("User Type OU = " + userTypeOu);
    return LdapNameBuilder.newInstance().add("ou", "users").add("cn", userFullName).build();
  }

  public Name buildGroupDnName(String groupName) {
    return LdapNameBuilder.newInstance().add("ou", "Groups").add("cn", groupName).build();
  }
}
