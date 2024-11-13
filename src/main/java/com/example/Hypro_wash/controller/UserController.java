package com.example.Hypro_wash.controller;

import com.example.Hypro_wash.models.User;
import com.example.Hypro_wash.repository.UserRepository;
import com.example.Hypro_wash.request.AuthenticationRequest;
import com.example.Hypro_wash.request.UserRegistrationRequest;
import com.example.Hypro_wash.response.AuthenticationResponse;

import com.example.Hypro_wash.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint pour l'authentification
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());


        return ResponseEntity.ok(new AuthenticationResponse(String.valueOf(jwtUtil.generateToken(userDetails))));
    }

    // Endpoint pour l'inscription
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword())); // Vous avez oublié le mot de passe ici
        user.setRoles("USER"); // Définissez les rôles nécessaires

        try {
            userRepository.save(user); // Assurez-vous que `userRepository` est injecté et configuré pour interagir avec votre base de données
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User registration failed");
        }
    }
}