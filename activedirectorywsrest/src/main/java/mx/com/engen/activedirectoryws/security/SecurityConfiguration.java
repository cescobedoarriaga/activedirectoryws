/**
 * 
 */
package mx.com.engen.activedirectoryws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Carlos Escobedo Arriaga
 *
 */

@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * The constant AUTH_WHITELIST.
   */
  private static final String[] AUTH_WHITELIST = { "/actuator/**", "/swagger-resources/**", "/swagger-ui/**",
      "/v3/api-docs/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/login/**", "/", "/users/**", "/groups/**",
      "/accounts/**" };

  /**
   * Configure.
   *
   * @param web the web
   */
  @Override
  public void configure(final WebSecurity web) {
    web.ignoring().antMatchers(HttpMethod.OPTIONS);
  }

  /**
   * Configure.
   *
   * @param http the http
   * @throws Exception the exception
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated();
  }
}
