package com.seneca.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


/**
 * This is a configuration class for setting up swagger open API.
 *
 * @author hemanth.nagireddy
 *
 */
@Configuration
public class SwaggerConfiguration {

  /**
   * A constant variable.
   */
  private static final String SECURITY = "Security";

  /**
   * This is the bean used for setting up swagger.
   *
   * @return returns the openAPI bean.
   */
  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI()
        .info(new Info().title("To-Do-API").version("1.0.0").description(
            "This API enables the users to save their daily tasks."))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY))
        .components(new Components().addSecuritySchemes(SECURITY,
            new SecurityScheme().name(SECURITY).type(SecurityScheme.Type.HTTP)
                .scheme("bearer")));
  }
}
