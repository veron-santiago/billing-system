package com.veron_santiago.facturas_api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.veron_santiago.facturas_api.service.implementations.entity.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${security.jwt.private-key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    private static final long EXPIRATION_TIME_DEFAULT = 86400000L;
    private static final long EXPIRATION_TIME_REMEMBER_ME = 604800000L;

    @Autowired
    private CompanyServiceImpl companyService;

    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        String  companyName = authentication.getPrincipal().toString();
        String email = companyService.getEmailByCompanyName(companyName);

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(companyName)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_DEFAULT))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userGenerator)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException e){
            throw new JWTVerificationException("Invalid Token");
        }
    }

    public String extractCompanyName(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

    public String extractEmailFromToken(String token){
        DecodedJWT decodedJWT = validateToken(token);
        return decodedJWT.getClaim("email").asString();
    }

    public String generateVerificationToken(String email){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_DEFAULT))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}
