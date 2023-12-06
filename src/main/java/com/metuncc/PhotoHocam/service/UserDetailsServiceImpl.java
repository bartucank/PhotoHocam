package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class represents user details service class. Actually this class
 * implements org.springframework.security.core.userdetails.UserDetailsService class for security implementation.
 * @see org.springframework.security.core.userdetails.UserDetailsService
 * @author bartu can palamut
 * **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user.toJwtUserDetails();
    }
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user = userRepository.getById(id);
        return user.toJwtUserDetails();
    }
}
