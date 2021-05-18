/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Repository
public interface AdTelephoneRepository extends LdapRepository<AdTelephone> {
  AdTelephone findByuserLogonName(String userLogonName);
}
