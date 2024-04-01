package com.example.carRental.service;

import com.example.carRental.controllers.Request.LoginRequest;
import com.example.carRental.controllers.Request.RegisterRequest;
import com.example.carRental.controllers.Response.AuthenticationResponse;
import com.example.carRental.controllers.Response.UserResponse;
import com.example.carRental.entity.User;
import com.example.carRental.helper.MapEntities;
import com.example.carRental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        var user= userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        var jwtToken= jwtService.generateToken((UserDetails) user);

        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    public AuthenticationResponse register(RegisterRequest registerRequest){
        var user1= User
                .builder()
                .lastName(registerRequest.getLastName())
                .firstName(registerRequest.getFirstName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user1);
        try{
            userRepository.save(user1);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exists!");
        }

        var jwtToken = jwtService.generateToken((UserDetails) user1);

        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    public UserResponse getUser(String token) {
        String email=jwtService.extractUsername(token);
        if(userRepository.existsByEmail(email)){
            User user=userRepository.findByEmail(email).get();
            return MapEntities.mapUserToUserResponse(user);
        }
        else throw new RuntimeException("Incorrect token");
    }
}
