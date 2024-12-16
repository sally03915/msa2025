package com.company.gatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component 
@Slf4j
public class Step4_CustomFilter extends AbstractGatewayFilterFactory<Step4_CustomFilter.Config> {
    public Step4_CustomFilter() { super(Config.class); }

    @Override
    public GatewayFilter apply(Config config) { 
        // Custom Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("...........................Custom PRE filter: request id -> {}", request.getId());
            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info(".........................Custom POST filter: response code -> {}", response.getStatusCode());
            }));
        };
    }
    public static class Config { }
} 
