package mx.com.engen.activedirectoryws.model.reqresp;

import javax.validation.Valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import mx.com.engen.activedirectoryws.ldap.repository.AdUser;

@EqualsAndHashCode(callSuper = false)
public class CreateUserRequest{
  /**
   * User.
   */
  @Setter
  @Getter
  @Valid
  private AdUser user;

}
