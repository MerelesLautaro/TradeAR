package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.AuthLoginRequestDTO;
import com.lautadev.tradear.dto.AuthLoginResponseDTO;
import com.lautadev.tradear.model.GoogleUserInfo;
import com.lautadev.tradear.service.CustomOidcUserService;
import com.lautadev.tradear.service.IUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private IUserDetailsService userDetailsService;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/login-google")
    public ResponseEntity<String> login(@RequestBody @Valid GoogleUserInfo googleUserInfo) {
        try {
            OidcUser oidcUser = customOidcUserService.processGoogleUser(googleUserInfo);
            return ResponseEntity.ok("User authenticated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }
}