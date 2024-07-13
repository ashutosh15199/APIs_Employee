package com.org.EmployeemanagementSystem.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Management",
                description="This is a Simple Project For Employee",
                termsOfService="T&C",
                contact=@Contact(
                        name = "Ashutosh Kumar Tiwari",
                        email = "ashutoshkumartiwari686@gmail.com"
                ),
                license = @License(
                        name = "Licence no"
                ),
                version = "v1"



        ),
        servers = {
        @Server(
                description = "Dev",
                url = "http://localhost:8084/myapp"
        ),
                @Server(
                        description = "test",
                        url = "http://localhost:8084"
                )
},
security=@SecurityRequirement(
        name = "auth"
)
)
@SecurityScheme(
        name = "auth",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "security docs"
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
