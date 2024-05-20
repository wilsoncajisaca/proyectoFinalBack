package com.example.demo.security;

import com.example.demo.commons.Constants;
import com.example.demo.tools.ExceptionTool;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider extends ExceptionTool {
    @Autowired
    private Constants constants;
    private Integer tokenTime;

    public String generateToken(String username, Integer tokenTime) {
        this.tokenTime = tokenTime;
        return doGenerateToken(username);
    }

    private String doGenerateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username).build();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, constants.getSIGNING_KEY())
                .setExpiration(new Date(System.currentTimeMillis() + tokenTime * 1000))
                .compact();
    }

    public String getUsernameOfToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(constants.getSIGNING_KEY()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * Validates if a token has the correct unmalformed signature and is not expired or unsupported.
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(constants.getSIGNING_KEY()).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            throw incorrectCredentials("Firma del token invalida");
        } catch (MalformedJwtException ex) {
            throw incorrectCredentials("Token invalida");
        } catch (ExpiredJwtException ex) {
            throw incorrectCredentials("El token expiró");
        } catch (UnsupportedJwtException ex) {
            throw incorrectCredentials("Token no soportado");
        } catch (IllegalArgumentException ex) {
            throw incorrectCredentials("Token invalido");
        }
    }
}
