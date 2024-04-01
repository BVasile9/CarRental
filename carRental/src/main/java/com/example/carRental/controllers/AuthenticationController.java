package com.example.carRental.controllers;

import com.example.carRental.controllers.Request.LoginRequest;
import com.example.carRental.controllers.Request.RegisterRequest;
import com.example.carRental.controllers.Response.AuthenticationResponse;
import com.example.carRental.controllers.Response.UserResponse;
import com.example.carRental.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @GetMapping("/extractUser")
    public ResponseEntity<UserResponse> getUser(@RequestParam("token")String token){
        return ResponseEntity.ok(authenticationService.getUser(token));
    }
}
