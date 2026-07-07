package com.idat.soporte.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI soporteTecnicoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Soporte Tecnico")
                        .description("API RESTful para registrar, consultar, actualizar y eliminar " +
                                "solicitudes de soporte tecnico. Proyecto academico - IDAT.")
                        .version("v1.0.0")
                        .contact(new Contact().name("Equipo de Desarrollo Backend")));
    }
}
