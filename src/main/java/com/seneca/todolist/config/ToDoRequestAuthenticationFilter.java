package com.seneca.todolist.config;

import com.seneca.todolist.entity.JwtBlockListEntity;
import com.seneca.todolist.exception.InvalidRequestException;
import com.seneca.todolist.model.UserDto;
import com.seneca.todolist.repository.IJwtBlockListRepository;
import com.seneca.todolist.service.UserService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * This is the filter used for authentication purposes. It extends
 * OncePerRequestFilter to perform filter only once per every request.
 *
 * @author hemanth.nagireddy
 *
 */
@Component
public class ToDoRequestAuthenticationFilter extends OncePerRequestFilter {

  /**
   * This is the autowired instance for user service interface.
   */
  @Autowired
  private UserService iuserService;

  /**
   * This is an instance of JwtTokenProvider autowired to use the validation
   * functionality of token.
   */
  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  /**
   * This interface connects with the database via java persistence api and used
   * for interacting with database. Used for block list entity.
   */
  @Autowired
  private IJwtBlockListRepository iJwtBlockListRepository;

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain)
      throws ServletException, IOException {

    logger.info("Inside the filter for authentication");
    final String token = request.getHeader("Authorization");
    String email = null;
    String jwtToken = null;
    if (token != null && token.startsWith("Bearer ")) {
      jwtToken = token.substring(7);
      try {
        email = jwtTokenProvider.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        logger.info("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        logger.info("JWT Token has expired");
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }

    if (email != null
        && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDto userDto = iuserService.findUserByEmail(email);
      String tokenSpecificId = jwtTokenProvider.getTokenSpecificIdFromToken(jwtToken);
      logger.info("tokenSpecificId:" + tokenSpecificId);
      JwtBlockListEntity entity = iJwtBlockListRepository.
          findByTokenSpecificIdAndEmail(tokenSpecificId, userDto.getEmail());
      if (Objects.nonNull(entity)) {
        throw new InvalidRequestException("Please authenticate");
      }
      if (jwtTokenProvider.validateToken(jwtToken, userDto)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
            new UsernamePasswordAuthenticationToken(
            userDto, userDto.getPassword(), /* userDTO.getAuthorities() */null);
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext()
            .setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

}
