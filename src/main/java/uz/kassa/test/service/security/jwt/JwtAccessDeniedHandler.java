package uz.kassa.test.service.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import uz.kassa.test.dto.ApiResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Author: Khumoyun Khujamov Date: 11/14/20 Time: 12:29 PM */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException, ServletException {
    // This is invoked when user tries to access a secured REST resource without the necessary
    // authorization
    // We should just send a 403 Forbidden response because there is no 'error' page to redirect to
    // Here you can place any message you want

    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

    final Map<String, Object> body = new HashMap<>();
    body.put("status", HttpServletResponse.SC_FORBIDDEN);
    body.put("error", "Forbidden");
    body.put("message", e.getMessage());
    body.put("path", httpServletRequest.getServletPath());
    final ApiResponse apiResponse = new ApiResponse(0, body);

    final ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(httpServletResponse.getOutputStream(), apiResponse);
    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
  }
}
