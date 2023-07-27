package com.employee.Group.controllers;

import com.employee.Group.dto.AuthResponse;
import com.employee.Group.dto.LoginDTO;
import com.employee.Group.dto.RegisterDTO;
import com.employee.Group.exceptions.IllegalRegisterDataException;
import com.employee.Group.security.JWTGenerator;
import com.employee.Group.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JWTGenerator jwtGenerator){
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(new AuthResponse(jwtGenerator.generateToken(authentication), new Date()),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerLogin(@Valid @RequestBody RegisterDTO registerDTO,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            throw new IllegalRegisterDataException(errors);
        }
        if(this.userService.userExistByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("This username already taken.",HttpStatus.BAD_REQUEST);
        }
        this.userService.saveUserByInfo(registerDTO);
        return new ResponseEntity<>("Registered",HttpStatus.CREATED);
    }
}
