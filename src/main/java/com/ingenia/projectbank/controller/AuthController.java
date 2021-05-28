package com.ingenia.projectbank.controller;

import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.model.modelTemp.AuthenticationRequest;
import com.ingenia.projectbank.model.modelTemp.AuthenticationResponse;
import com.ingenia.projectbank.security.JWTUtil;
import com.ingenia.projectbank.service.ServiceImpl.UserDetailsServiceImpl;
import com.ingenia.projectbank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://bank-ingenia-front.vercel.app", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;

    public AuthController(UserService userService, UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest userRequest) {
        if (userService.existsByEmailAndPassword(userRequest.getEmail(),userRequest.getPassword())) {
            User UserEncontrado=userService.findUserByEmail(userRequest.getEmail());
            UserDetails userDetails = userDetailsService.loadUserByUsername(UserEncontrado.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/registro")
    public ResponseEntity<User> registro(@RequestBody AuthenticationRequest userRequest) {
        if (userRequest.getPassword()==null||userRequest.getEmail()==null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            User user= new User("","",userRequest.getEmail(),userRequest.getPassword());
            User userCreado=userService.createUser(user);
            return ResponseEntity.ok().body(userCreado);
        }
    }
}
