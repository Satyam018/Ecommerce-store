package com.example.auth_services.service;


import com.example.auth_services.dao.UserRepository;
import com.example.auth_services.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public ResponseEntity<String> addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User Added Successfully!", HttpStatus.OK);
    }

    public String generateToken(String userEmail){
        return jwtService.generateToken(userEmail);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }


}
