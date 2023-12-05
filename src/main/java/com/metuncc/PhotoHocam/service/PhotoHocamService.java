package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.domain.Image;
import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.dto.Imagedto;
import com.metuncc.PhotoHocam.repository.ImageRepository;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoHocamService {

    private UserRepository userRepository;
    private ImageRepository imageRepository;
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

    public boolean sendImage(MultipartFile file, Long userID) {

        User receiver = userRepository.getById(userID);
        User sender = userRepository.getById(getCurrentUserId());

        if (receiver == null) {
            return false;
        }
        try {
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());

            Image img = new Image();
            img.setData(base64);
            img.setReceiver(receiver);
            img.setSender(sender);

            imageRepository.save(img);



        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void deleteImage(Long id){

        imageRepository.deleteById(id);

    }
    public List<Imagedto> getImagelist(){

        Long me = getCurrentUserId();
        List<Image> imgs = imageRepository.getImages(me);
        List<Imagedto> result = new ArrayList<>();
        for (Image img : imgs) {

            Boolean check = false;
            for (Imagedto imagedto : result) {
                if(imagedto.getUsername().equals(img.getSender().getUsername())){
                    check = true;
                    imagedto.getImageList().add(img);
                }
            }
            if(!check){
                Imagedto imagedto = new Imagedto();
                imagedto.setUsername(img.getSender().getUsername());
                imagedto.setImageList(new ArrayList<>());
                imagedto.getImageList().add(img);
            }


        }

        return result;

    }


}
