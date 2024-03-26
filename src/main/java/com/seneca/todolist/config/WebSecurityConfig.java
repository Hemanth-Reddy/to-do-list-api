package com.seneca.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class specifies all the security related configurations.
 * It overrides the WebSecurityConfigurerAdapter class provided by Spring Security.
 * <ul>
 *  <li>Authorization</li>
 *  <li>Filter configuration</li>
 *  <li>Session related configurations</li>
 * </ul>
 *
 * @author hemanth.nagireddy
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   *Bean for authentication entry point.
   */
  @Autowired
  private ToDoAuthenticationEntryPoint toDoAuthenticationEntryPoint;

  /**
   * Bean for authentication filter to filterout the requests.
   */
  @Autowired
  private ToDoRequestAuthenticationFilter toDoRequestAuthenticationFilter;

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf().disable().authorizeRequests()
            //.antMatchers("/v3/**", "/swagger-ui/**", "/user/login", "/user/register")
            .antMatchers("/**")
            .permitAll().anyRequest().authenticated().and().exceptionHandling()
            .authenticationEntryPoint(toDoAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(toDoRequestAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);
  }
}