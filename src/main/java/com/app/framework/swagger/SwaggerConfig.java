package com.app.framework.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer",
  description = "A JWT acess token will be used for authentication."
)
@OpenAPIDefinition(
    info = @Info(
        description = "A ordering system API.",
        license = @License(
                name = "MIT"
        ),
        version = "1.0",
        title = "Ordering system API."
    )
)
public class SwaggerConfig {}
