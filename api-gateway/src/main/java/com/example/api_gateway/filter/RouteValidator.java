package com.example.api_gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class RouteValidator {

    private final List<String> OPEN_END_POINT=List.of(
            "/auth/register",
            "/auth/token"
    );


    public boolean isSecured(ServerHttpRequest request){
        String requestPath=request.getURI().getPath();
        for (String uri:OPEN_END_POINT){
            if(requestPath.contains(uri))return false;
        }
        return true;
    }

    public boolean hasAuthorised(ServerWebExchange serverWebExchange){
        return serverWebExchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }


}
