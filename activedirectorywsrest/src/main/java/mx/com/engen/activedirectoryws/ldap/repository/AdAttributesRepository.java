/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import org.springframework.data.ldap.repository.LdapRepository;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public interface AdAttributesRepository extends LdapRepository<AdAttributes>{
  AdAttributes findByuserLogonName(String userLogonName);
  AdAttributes findByFullName(String fullName);
}
