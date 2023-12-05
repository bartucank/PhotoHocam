package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.domain.FriendRequest;
import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.repository.FriendRequestRepository;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class PhotoHocamService {

    private UserRepository userRepository;
    private FriendRequestRepository friendRequestRepository;
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

    public void sendFriendRequest(Long receiver){
        if(Objects.isNull(userRepository.getById(getCurrentUserId()))){
            throw new RuntimeException("User not found");
        }
        if(Objects.isNull(userRepository.getById(receiver))){
            throw new RuntimeException("Receiver not found");
        }
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(getCurrentUserId());
        friendRequest.setReceiver(receiver);
        friendRequestRepository.save(friendRequest);
        return;
    }
    public void approveFriendRequest(Long id){
        FriendRequest friendRequest = friendRequestRepository.getById(id);
        if(Objects.isNull(friendRequest)){
            throw new RuntimeException("Friend request not found");
        }
        User sender = userRepository.getById(friendRequest.getSender());
        User receiver = userRepository.getById(friendRequest.getReceiver());

        if(Objects.isNull(sender.getFriends())){
            sender.setFriends(new ArrayList<>());
        }
        sender.getFriends().add(receiver);
        userRepository.save(sender);

        if(Objects.isNull(receiver.getFriends())){
            receiver.setFriends(new ArrayList<>());
        }
        receiver.getFriends().add(sender);
        userRepository.save(receiver);

        friendRequestRepository.delete(friendRequest);
    }

}
