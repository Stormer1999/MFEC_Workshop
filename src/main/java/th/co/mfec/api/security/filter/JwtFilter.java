package th.co.mfec.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.model.common.ErrorResponse;
import th.co.mfec.api.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String jwtToken = request.getHeader("Authorization");
    if (jwtToken != null && jwtToken.startsWith("Bearer")) {
      jwtToken = jwtToken.substring(7);
      try {
        jwtUtil.validateToken(jwtToken);
      } catch (Exception ex) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse<Object> errorResponse =
            new ErrorResponse<>(StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
      }

      String username = jwtUtil.getUsernameFromJwt(jwtToken);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(username, "(protected)", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
