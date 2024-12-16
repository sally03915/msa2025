package com.company.gatewayservice.filter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Step2_FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/ms1-service/**")
                            .filters(f -> f.addRequestHeader("ms1-request", "ms1-request-header")
                                           .addResponseHeader("ms1-response", "ms1-response-header"))
                            .uri("http://localhost:8081"))
                .route(r -> r.path("/ms2-service/**")
	                        .filters(f -> f.addRequestHeader("ms2-request", "ms2-request-header")
	                                .addResponseHeader("ms2-response", "ms2-response-header"))
	                        .uri("http://localhost:8082"))
                .build();
    }
}