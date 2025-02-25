package com.example.api_gateway.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTService {

    @Value("${jwt.secret}")
    private  String SECRET;


    public boolean validateToken(String token){
        Jws<Claims> claimsJws=Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        return true;
    }



    private Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
