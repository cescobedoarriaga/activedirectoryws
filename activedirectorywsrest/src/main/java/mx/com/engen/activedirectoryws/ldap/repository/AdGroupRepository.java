/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap.repository;

import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public interface AdGroupRepository extends LdapRepository<AdGroup> {
  List<AdGroup> findAll();
  AdGroup findByGroupName(String cn);
}
