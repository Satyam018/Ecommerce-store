package com.example.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter implements WebFilter {

    @Autowired
    RouteValidator routeValidator;

    @Autowired
    private JWTService jwtService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
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
}