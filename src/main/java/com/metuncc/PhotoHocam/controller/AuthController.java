package com.metuncc.PhotoHocam.controller;


import com.metuncc.PhotoHocam.security.JwtProvider;
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

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest){
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
//                        userRequest.getPass());
//        Authentication authentication = authenticationManager
//                .authenticate(usernamePasswordAuthenticationToken);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token =  jwtTokenProvider.generateJwtToken(authentication);
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setJwt(token);
//        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<LoginResponse> register(@RequestBody UserRequest userRequest){
//       try{
//           mlmCommandServices.createUser(userRequest);
//           return login(userRequest);
//       }catch (Exception e){
//           return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//       }
//    }
}
