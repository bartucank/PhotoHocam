package com.metuncc.PhotoHocam.controller;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.controller.response.ImageListResponse;
import com.metuncc.PhotoHocam.controller.response.LoginResponse;
import com.metuncc.PhotoHocam.dto.ImageListDTO;
import com.metuncc.PhotoHocam.security.JwtProvider;
import com.metuncc.PhotoHocam.service.PhotoHocamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value ="/api", produces = "application/json;charset=UTF-8")
public class Controller {
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtTokenProvider;
    private PhotoHocamService photoHocamService;

    public Controller(AuthenticationManager authenticationManager, JwtProvider jwtTokenProvider, PhotoHocamService photoHocamService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.photoHocamService = photoHocamService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                        userRequest.getPass());
        Authentication authentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =  jwtTokenProvider.generateJwtToken(authentication);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwt(token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserRequest userRequest){
       try{
           photoHocamService.createUser(userRequest);
           return new ResponseEntity<>(true,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);       }
    }


    @PostMapping("/user/addFriend")
    public ResponseEntity<Boolean> addFriend(@RequestParam Long id){
        photoHocamService.sendFriendRequest(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
    @PostMapping("/user/approveFriendRequest")
    public ResponseEntity<Boolean> approveFriendRequest(@RequestParam Long id){
        photoHocamService.approveFriendRequest(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
    @PostMapping("/user/sendImage")
    public ResponseEntity<Boolean> sendImage(@RequestBody MultipartFile file,
                                             @RequestParam Long userid){
        photoHocamService.sendImage(file, userid);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
    @GetMapping("/user/getImagelist")
    public ResponseEntity<ImageListResponse> getImagelist(){
        ImageListResponse result = new ImageListResponse();
        result.setImageListDTOList(photoHocamService.getImagelist());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
