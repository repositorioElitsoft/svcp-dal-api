package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.security.AuthResponse;
import com.elitsoft.servicampo.security.AuthenticationRequest;
import com.elitsoft.servicampo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elitsoft.servicampo.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; // Spring Security's AuthenticationManager
    @Autowired
    private JwtTokenProvider jwtTokenProvider; // Your JWT utility class (see below)
    @Autowired
    private UserMapper userMapper; // Your MyBatis UserRepository

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {

        //String password = "1234";

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = passwordEncoder.encode(password);

        //System.out.println("BCrypt hash: " + hashedPassword);

        try {
            System.out.println("/login mehtod()");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );



            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("/login  SecurityContextHolder.getContext().setAuthentication(authentication);");

            String jwt = jwtTokenProvider.generateToken(authentication); // Generate JWT
            System.out.println("/login 200 OK");
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (AuthenticationException e) {
            System.out.println("/login 401 UNAUTHORIZED");
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Handle authentication failure
        }
    }

    // ... AuthenticationRequest and AuthResponse classes ...
}