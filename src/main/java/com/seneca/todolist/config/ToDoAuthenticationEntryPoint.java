package com.seneca.todolist.config;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This is an authentication entry point method which commences the
 * authentication process.
 *
 * @author hemanth.nagireddy
 */
@Component
public class ToDoAuthenticationEntryPoint
    implements AuthenticationEntryPoint, Serializable {

  @Override
  public void commence(final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException authenticationException)
      throws IOException {

    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }

}