package com.example.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter implements GatewayFilterFactory<AuthFilter.Config> {




    @Autowired
    RouteValidator routeValidator;

    @Autowired
    private JWTService jwtService;


    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest serverHttpRequest=exchange.getRequest();
                if (routeValidator.isSecured(serverHttpRequest)){
                    if(!routeValidator.hasAuthorised((ServerWebExchange) serverHttpRequest)){
                        throw new RuntimeException("Missing Authoriztaion Header");
                    }

                    String token=serverHttpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                    if(token!=null && token.startsWith("Bearer ")){
                        token=token.substring(7);
                    }
                    if (!jwtService.validateToken(token)){
                        throw new RuntimeException("Invalid Token or Token Expired");
                    }
                }


                return chain.filter(exchange);
            }
        };

    }


    public static class Config{}

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }



}