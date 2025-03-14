package com.veron_santiago.facturas_api.presentation.controller;

import com.veron_santiago.facturas_api.presentation.dto.auth.AuthCreateCompany;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthRequest;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthResponse;
import com.veron_santiago.facturas_api.service.implementations.CompanyServiceImpl;
import com.veron_santiago.facturas_api.service.implementations.UserDetailsServiceImpl;
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
public class  AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        return new ResponseEntity<>(userDetailsService.loginUser(authRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateCompany authCreateCompany){
        return new ResponseEntity<>(userDetailsService.createCompany(authCreateCompany), HttpStatus.CREATED);
    }

}