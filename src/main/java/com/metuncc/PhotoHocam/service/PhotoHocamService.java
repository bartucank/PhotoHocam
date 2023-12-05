package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PhotoHocamService {

    private UserRepository userRepository;

}
