/**
 * 
 */
package mx.com.engen.activedirectoryws.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import mx.com.engen.activedirectoryws.exception.GlobalRuntimeException;
import mx.com.engen.activedirectoryws.util.Constants;

/**
 * @author Carlos Escobedo Arriaga
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  /**
   * The constant HEADER.
   */
  private static final String HEADER = "Authorization";
  /**
   * The constant PREFIX.
   */
  private static final String PREFIX = "Bearer ";

  /**
   * Do filter internal.
   *
   * @param request  the request
   * @param response the response
   * @param chain    the chain
   * @throws ServletException the servlet exception
   * @throws IOException      the io exception
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    try {
      if (existeJWTToken(request)) {
        Claims claims = validateToken(request);
        if (claims.get("authorities") != null) {
          setUpSpringAuthentication(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
      } else {
        SecurityContextHolder.clearContext();
      }
      chain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      throw new GlobalRuntimeException(e);
    }
  }

  /**
   * Validate token claims.
   *
   * @param request the request
   * @return the claims
   */
  private static Claims validateToken(HttpServletRequest request) {
    String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
    return Jwts.parser().setSigningKey(Constants.SECRET.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwtToken)
        .getBody();
  }

  /**
   * Sets up spring authentication.
   *
   * @param claims the claims
   */
  private static void setUpSpringAuthentication(Claims claims) {
    List<String> authorities = (List<String>) claims.get("authorities");

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    SecurityContextHolder.getContext().setAuthentication(auth);

  }

  /**
   * Existe jwt token boolean.
   *
   * @param request the request
   * @return the boolean
   */
  private static boolean existeJWTToken(HttpServletRequest request) {
    String authenticationHeader = request.getHeader(HEADER);
    return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
  }

}
