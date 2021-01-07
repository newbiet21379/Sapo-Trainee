package com.sapo.edu.jwt.controller;


import com.sapo.edu.jwt.message.request.LoginForm;
import com.sapo.edu.jwt.message.request.SignUpForm;
import com.sapo.edu.jwt.message.response.JwtResponse;
import com.sapo.edu.jwt.model.Role;
import com.sapo.edu.jwt.model.RoleName;
import com.sapo.edu.jwt.model.User;
import com.sapo.edu.jwt.repository.RoleRepository;
import com.sapo.edu.jwt.repository.UserRepository;
import com.sapo.edu.jwt.security.jwt.JwtProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        System.out.println("username:"+loginRequest.getUsername());
        System.out.println("password:"+loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
       User user=userRepository.findByUsername(loginRequest.getUsername()).get();
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt,user));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@NotNull @Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(signUpRequest.getUsername()
                , encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "Full":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_Full).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;

                case "Read_Only":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_Read_Only)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
                    break;
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}