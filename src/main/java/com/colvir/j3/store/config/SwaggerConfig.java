package com.colvir.j3.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableOpenApi
//@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
//        HttpAuthenticationScheme authenticationScheme
//                = HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();

//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .securityContexts(Collections.singletonList(securityContext()))
//                .securitySchemes(Collections.singletonList(authenticationScheme));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private @NotNull List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MButorin Store REST API",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("Mikhail Butorin", "www.colvir.com", "mbutorin@colvir.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
