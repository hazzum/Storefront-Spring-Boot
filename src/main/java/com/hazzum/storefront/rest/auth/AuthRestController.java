package com.hazzum.storefront.rest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.payload.request.LoginRequest;
import com.hazzum.storefront.payload.response.JwtResponse;
import com.hazzum.storefront.payload.response.MessageResponse;
import com.hazzum.storefront.DAO.UserRepository;
import com.hazzum.storefront.security.jwt.JwtUtils;
import com.hazzum.storefront.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users/")
public class AuthRestController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/sign_in")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();   

    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
  }

  @PostMapping("/sign_up")
  public ResponseEntity<?> registerUser(@RequestBody User signUpRequest) {
    // Create new user's account
    User user = new User(signUpRequest.getFirstName(), 
               signUpRequest.getLastName(),
               signUpRequest.getUserName(),
               encoder.encode(signUpRequest.getPassword()));
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
