package com.example.auth_services.controllers;


import com.example.auth_services.entity.AddUserDTO;
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
    public ResponseEntity<String> addNewUser(@RequestBody AddUserDTO addUserDTO){

        return authService.addUser(addUserDTO);
    }


    @PostMapping("/token")
    public String getToken(@RequestBody AddUserDTO addUserDTO){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(addUserDTO.getEmail(),addUserDTO.getPassword()));
       if(authentication.isAuthenticated()) return authService.generateToken(addUserDTO.getEmail());
       else throw new IllegalArgumentException("User is not registerted!");
    }

//    @GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token){
//         authService.validateToken(token);
//         return "Token is Valid!";
//    }




}
