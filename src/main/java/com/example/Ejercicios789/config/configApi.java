package com.example.Ejercicios789.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configApi {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info().title("Spring boot Laptop API REST")
                        .description("Laptops Api rest docs")
                        .contact(new Contact().email("Jhon@example.com").name("Jhon"))
                        .version("1.0")
                        .license(new License().name("Laptop API").url("https://www.google.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring boot laptop Documentation")
                        .url("https://www.google.com"));

    }
}
