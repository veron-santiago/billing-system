package com.veron_santiago.facturas_api.presentation.controller;

import com.veron_santiago.facturas_api.presentation.dto.auth.AuthCreateCompany;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthRequest;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthResponse;
import com.veron_santiago.facturas_api.service.implementations.entity.CompanyServiceImpl;
import com.veron_santiago.facturas_api.service.implementations.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class  AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CompanyServiceImpl companyService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        return new ResponseEntity<>(userDetailsService.loginUser(authRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateCompany authCreateCompany){
        return new ResponseEntity<>(userDetailsService.createCompany(authCreateCompany), HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token){
        boolean verified = companyService.verifyEmail(token);
        if(verified) return ResponseEntity.ok("El correo electrónico se ha verificado correctamente.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha ocurrido un error al verificar el correo electrónico");
    }
}