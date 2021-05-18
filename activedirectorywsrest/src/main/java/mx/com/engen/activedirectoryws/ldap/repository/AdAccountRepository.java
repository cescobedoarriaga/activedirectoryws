  /**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
@Repository
public interface AdAccountRepository extends LdapRepository<AdAccount> {
  @Override
  List<AdAccount> findAll();
  AdAccount findByuserLogonName(String userLogonName);
  AdAccount findByFullName(String fullName);
}
