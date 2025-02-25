package com.example.auth_services.controllers;


import com.example.auth_services.entity.User;
import com.example.auth_services.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user){

        return authService.addUser(user);
    }


    @PostMapping("/token")
    public String getToken(@RequestBody User user){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
       if(authentication.isAuthenticated()) return authService.generateToken(user.getEmail());
       else throw new IllegalArgumentException("User is not registerted!");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
         authService.validateToken(token);
         return "Token is Valid!";
    }




}
