package com.example.auth_services.service;

import com.example.auth_services.entity.AddUserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<String> addUser(AddUserDTO addUserDTO);

    public String generateToken(String userName);

    public void validateToken(String token);
}
