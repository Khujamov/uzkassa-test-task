package uz.kassa.test.service.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.kassa.test.service.security.LoggedUser;


import java.time.Instant;
import java.util.Date;

/** Author: Khumoyun Khujamov Date: 11/13/20 Time: 2:40 PM */
@Component
public class JwtTokenProvider {

  private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${jwt.base64-secret}")
  private String jwtSecret;

  @Value("${jwt.token-validity-in-seconds}")
  private int jwtExpirationMs;

  public String generateToken(LoggedUser loggedUser) {

    //        // User roles
    //        Set<Role> roles = userPrincipal.getRoles();

    // Setting claims
    Claims claims = Jwts.claims().setSubject(loggedUser.getId().toString());
    //        claims.put("roles",roles.stream().map(role -> new
    // SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toSet()));

    // Expiration Date
    Instant expiryDate = Instant.now().plusSeconds(jwtExpirationMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(expiryDate))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserIdFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
