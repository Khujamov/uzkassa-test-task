package uz.kassa.test.service.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.kassa.test.service.security.CustomUserDetailsService;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Author: Khumoyun Khujamov Date: 11/13/20 Time: 3:33 PM */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired private JwtTokenProvider tokenProvider;

  @Autowired private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    String jwt = getJwtFromRequest(httpServletRequest);

    if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
      // get userid from jwt
      String userId = tokenProvider.getUserIdFromJwtToken(jwt);

      // load current user from db
      UserDetails userDetails = customUserDetailsService.loadUserById(Long.parseLong(userId));

      //
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
