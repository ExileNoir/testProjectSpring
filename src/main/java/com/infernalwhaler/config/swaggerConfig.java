package com.infernalwhaler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration to visually render documentation
 *
 * @author sDeseure
 * @project TestProject
 * @date 18/08/2021
 * @see <a href="http://localhost:8080/swagger-ui/">Swagger</a>
 */

@EnableSwagger2
@Configuration
public class swaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select().build();
    }
}
