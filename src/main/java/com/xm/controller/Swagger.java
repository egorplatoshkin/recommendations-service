package com.xm.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spi.service.contexts.Orderings;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {

    @Bean
    public Docket swaggerDocketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("recommendations-service")
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("Recommendation Service")
                                .description(
                                        "Service to collecting and calculating recommendation data for crypto investors.")
                                .version("1")
                                .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xm"))
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(true);
    }

    @Bean
    public InitializingBean removeSpringfoxHandlerProvider(DocumentationPluginsBootstrapper bootstrapper) {
        return () -> bootstrapper.getHandlerProviders().removeIf(WebMvcRequestHandlerProvider.class::isInstance);
    }

    @Bean
    public RequestHandlerProvider customRequestHandlerProvider(Optional<ServletContext> servletContext, HandlerMethodResolver methodResolver, List<RequestMappingInfoHandlerMapping> handlerMappings) {
        String contextPath = servletContext.map(ServletContext::getContextPath).orElse(Paths.ROOT);
        return () -> handlerMappings.stream()
                .filter(mapping -> !mapping.getClass().getSimpleName().equals("IntegrationRequestMappingHandlerMapping"))
                .map(mapping -> mapping.getHandlerMethods().entrySet())
                .flatMap(Set::stream)
                .map(entry -> new WebMvcRequestHandler(contextPath, methodResolver, updateMappingInfo(entry.getKey()), entry.getValue()))
                .sorted(Orderings.byPatternsCondition())
                .collect(Collectors.toList());
    }

    private RequestMappingInfo updateMappingInfo(RequestMappingInfo info) {
        if (info.getPathPatternsCondition() == null) return info;
        String[] patterns = info.getPathPatternsCondition().getPatternValues().toArray(String[]::new);
        return info.mutate().options(new RequestMappingInfo.BuilderConfiguration()).paths(patterns).build();
    }
}