package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PhotoHocamService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void createUser(UserRequest userRequest) {
        if(Objects.isNull(userRequest) || Objects.isNull(userRequest.getUsername()) || Objects.isNull(userRequest.getPass()) ){
            throw new RuntimeException("Unexpected Error");
        }
        if(Objects.nonNull(userRepository.findByUsername(userRequest.getUsername()))){
            throw new RuntimeException("Username already taken.");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPass()));
        userRepository.save(user);
        return;
    }

    public Long getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();
        return jwtUser.getId();
    }

}
