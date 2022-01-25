package com.seneca.todolist.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *This is an interceptor class which can be used to intercept the 
 *requests and responses before the controller.
 *
 *@author hemanth.nagireddy
 *
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

  /**
   * LOGGER instance created using the loggerfactory.getLogger method.
   */
  private static final Logger LOG = LoggerFactory
      .getLogger(AuthenticationInterceptor.class);

  /**
   * This method accesses the request before actually moving on with the
   * controller.
   */
  @Override
  public boolean preHandle(final HttpServletRequest request,
      final HttpServletResponse response, final Object handler) throws Exception {

    LOG.info(
        "Token present for the request. "
        + "The request header for authorization is - {} for this request.",
        request.getHeader("Authorization"));
    return true;
  }
}
