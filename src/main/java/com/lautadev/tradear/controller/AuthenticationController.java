package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.AuthLoginRequestDTO;
import com.lautadev.tradear.dto.AuthLoginResponseDTO;
import com.lautadev.tradear.service.IUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private IUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> login (@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}