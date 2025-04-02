package com.elitsoft.servicampo.controller.core;

import com.elitsoft.servicampo.common.api.response.ApiEnityResponse;
import com.elitsoft.servicampo.exceptions.EmpleadoAutenticacionException;
import com.elitsoft.servicampo.mapper.UserMapper;
import com.elitsoft.servicampo.security.AuthResponse;
import com.elitsoft.servicampo.security.AuthenticationRequest;
import com.elitsoft.servicampo.security.JwtTokenProvider;
import com.elitsoft.servicampo.service.error.EmpleadoError;
import com.elitsoft.servicampo.utils.Constantes;
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
    public ResponseEntity<ApiEnityResponse<AuthResponse>> login(@RequestBody AuthenticationRequest authRequest) {


        try {
            System.out.println("/login mehtod()");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken(authentication); // Generate JWT
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiEnityResponse<>(new AuthResponse(jwt))); // Retorna  201 Created
        } catch (EmpleadoAutenticacionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiEnityResponse<>(null, e.getErrorCode(), e.getMessage())); // Retorna  401 Unauthorized
        } catch (AuthenticationException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiEnityResponse<>(null, EmpleadoError.NO_AUTORIZADO.getCodigoError(), Constantes.EMPLEADO_NO_AUTORIZADO_MENSAJE)); // Retorna  401 Unauthorized
        }
    }


}