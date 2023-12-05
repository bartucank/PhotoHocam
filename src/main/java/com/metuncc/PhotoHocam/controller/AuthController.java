package com.metuncc.PhotoHocam.controller;


import com.metuncc.PhotoHocam.controller.request.UserRequest;
import com.metuncc.PhotoHocam.controller.response.LoginResponse;
import com.metuncc.PhotoHocam.security.JwtProvider;
import com.metuncc.PhotoHocam.service.PhotoHocamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/auth", produces = "application/json;charset=UTF-8")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtTokenProvider;
    private PhotoHocamService photoHocamService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
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
           return new ResponseEntity<>(true,HttpStatus.BAD_REQUEST);       }
    }
}
