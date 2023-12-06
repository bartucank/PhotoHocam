package com.metuncc.PhotoHocam.service;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.domain.Image;
import com.metuncc.PhotoHocam.domain.FriendRequest;
import com.metuncc.PhotoHocam.domain.User;
import com.metuncc.PhotoHocam.dto.Imagedto;
import com.metuncc.PhotoHocam.repository.ImageRepository;
import com.metuncc.PhotoHocam.repository.FriendRequestRepository;
import com.metuncc.PhotoHocam.repository.UserRepository;
import com.metuncc.PhotoHocam.security.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.zip.Deflater;

@Service
public class PhotoHocamService {

    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private FriendRequestRepository friendRequestRepository;
    private PasswordEncoder passwordEncoder;

    public PhotoHocamService(UserRepository userRepository, ImageRepository imageRepository, FriendRequestRepository friendRequestRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        user.setFriends(new ArrayList<>());
        userRepository.save(user);
        return;
    }

    public Long getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User jwtUser = (User) auth.getPrincipal();
        return jwtUser.getId();
    }

    public boolean sendImage(MultipartFile file, Long userID) {

        User receiver = userRepository.getById(userID);
        User sender = userRepository.getById(getCurrentUserId());

        if (receiver == null) {
            return false;
        }
        try {


            Image img = new Image();
            var deflater = new Deflater();
            deflater.setLevel(Deflater.BEST_COMPRESSION);
            deflater.setInput(file.getBytes());
            deflater.finish();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(file.getBytes().length);
            byte[] tmp = new byte[4*1024];
            while (!deflater.finished()) {
                int size = deflater.deflate(tmp);
                outputStream.write(tmp, 0, size);
            }
            try {
                outputStream.close();
            } catch (Exception e) {
            }
            img.setData( outputStream.toByteArray());
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
