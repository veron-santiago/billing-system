package com.veron_santiago.facturas_api.service.implementations;

import com.veron_santiago.facturas_api.persistence.entity.Company;
import com.veron_santiago.facturas_api.persistence.repository.ICompanyRepository;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthCreateCompany;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthRequest;
import com.veron_santiago.facturas_api.presentation.dto.auth.AuthResponse;
import com.veron_santiago.facturas_api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Company company = companyRepository.findByCompanyNameOrEmail(username)
                .orElseThrow( () -> new UsernameNotFoundException("Compañia no encontrada con el nombre o email: " + username));

        return User.builder()
                .username(company.getCompanyName())
                .password(company.getPassword())
                .build();
    }

    private Authentication authenticate(String companyName, String password){
        UserDetails userDetails = this.loadUserByUsername(companyName);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid company name or password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(companyName, userDetails.getPassword());
    };

    public AuthResponse loginUser(AuthRequest authRequest){
        String companyName = authRequest.companyName();
        String password = authRequest.password();

        Authentication authentication = this.authenticate(companyName, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = jwtUtil.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(companyName, "Company loged successfully", accesToken, true);
        return authResponse;
    }

    public AuthResponse createCompany(AuthCreateCompany authCreateCompany){
        String companyName = authCreateCompany.companyName();
        String password = authCreateCompany.password();
        String email = authCreateCompany.email();

        if (companyRepository.findByCompanyName(companyName).isPresent()) {
            return new AuthResponse(companyName, "La Compañia ya existe", null, false);
        }

        Company company = Company.builder()
                .companyName(companyName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        Company companyCreated = companyRepository.save(company);

        Authentication authentication = new UsernamePasswordAuthenticationToken(companyCreated.getCompanyName(), companyCreated.getPassword());
        String token = jwtUtil.createToken(authentication);
        return new AuthResponse(companyName, "Compañia registrada correctamente", token, true);
    }
}
