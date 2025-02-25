package com.example.auth_services.service;


import com.example.auth_services.config.CustomUserDetails;
import com.example.auth_services.dao.UserRepository;
import com.example.auth_services.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user=userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UsernameNotFoundException("User Not FOund!");
        return new CustomUserDetails(user.get().getEmail(),user.get().getPassword());
    }
}
