package com.instituto.restapi.Instituto.app.controller;

import com.instituto.restapi.Instituto.app.DTO.JwtAuthResponseDTO;
import com.instituto.restapi.Instituto.app.DTO.LoginDTO;
import com.instituto.restapi.Instituto.app.DTO.SignUpDTO;
import com.instituto.restapi.Instituto.app.entity.Role;
import com.instituto.restapi.Instituto.app.entity.User;
import com.instituto.restapi.Instituto.app.repository.RoleRepository;
import com.instituto.restapi.Instituto.app.repository.UserRepository;
import com.instituto.restapi.Instituto.app.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> logIn(@RequestBody LoginDTO loginDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginDTO.getUsernameOrEmail(),loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JwtAuthResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO){

        if(userRepository.existsByUsername(signUpDTO.getUsername())){

            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDTO.getEmail())){

            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();

        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setUsername(signUpDTO.getUsername());
        user.setPassword( passwordEncoder.encode(signUpDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN").get();

        user.setRoles(Collections.singleton(role));

        userRepository.save(user);



        return new ResponseEntity<>("User created", HttpStatus.OK);

    }
}
