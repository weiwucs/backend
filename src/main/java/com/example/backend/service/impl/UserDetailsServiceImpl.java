package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.entity.UserLogin;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("System security ");
        User user = userRepository.findUserByUsername(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("username not found");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new UserLogin(user);
    }
}
