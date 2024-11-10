package com.example.Hypro_wash.controller;

import com.example.Hypro_wash.models.User;
import com.example.Hypro_wash.repository.UserRepository;
import com.example.Hypro_wash.response.AuthenticationResponse;
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

        return ResponseEntity.ok(new AuthenticationResponse());
    }

    // Endpoint pour l'inscription
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.));
        user.setRoles("USER"); // Définissez les rôles nécessaires

        try {
            UserRepository.save(user); // Assurez-vous que `userRepository` est injecté et configuré pour interagir avec votre base de données
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User registration failed");
        }
    }
}