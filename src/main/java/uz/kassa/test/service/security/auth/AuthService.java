package uz.kassa.test.service.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.kassa.test.dto.JwtResponse;
import uz.kassa.test.service.security.LoggedUser;
import uz.kassa.test.service.security.jwt.JwtTokenProvider;

/** Author: Khumoyun Khujamov Date: 11/13/20 Time: 4:44 PM */
@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public String authUser(String username, String password) {
    Authentication authentication = authenticateUser(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
    return jwtTokenProvider.generateToken(loggedUser);
  }

  public Authentication authenticateUser(String username, String password) {
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
  }

  public JwtResponse prepareJwtResponse(String token) {
    return new JwtResponse(token);
  }
}
