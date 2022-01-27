package com.seneca.todolist;

import com.seneca.todolist.authentication.AuthenticationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *This is the main class.
 *
 *@author hemanth.nagireddy
 *
 *
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ToDolistApplication implements WebMvcConfigurer {

  /**
   *This is the main method.<br>
   *
   *@param args
   *
   */
  public static void main(final String[] args) {
    SpringApplication.run(ToDolistApplication.class, args);
  }

  /**
   * Adds any interceptors if required.
   *
   * @param registry Helps with configuring a list of mapped interceptors.
   */
  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(getInterceptor());
  }

  /**
   * This method adds the authentication interceptor as a bean during runtime.
   *
   *@return AuthenticationInterceptor bean is returned.
   */
  @Bean
  public AuthenticationInterceptor getInterceptor() {
    return new AuthenticationInterceptor();
  }

  /**
   *This is a bean for password encoder.
   *
   *@return BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
