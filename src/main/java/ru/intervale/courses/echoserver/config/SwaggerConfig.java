package ru.intervale.courses.echoserver.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("echoserver")
                                .version("0.0.1-SNAPSHOT")
                                .contact(
                                        new Contact()
                                                .email("zohan1234567@gmail.com")
                                                .url("https://github.com/EugeneZakhno")
                                                .name("Eugene Zakhno")
                                )
                );
    }

}