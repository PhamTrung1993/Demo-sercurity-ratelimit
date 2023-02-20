package com.example.demosercurityratelimit.service.jwt;

import com.example.demosercurityratelimit.model.UserPrinciple;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@Log4j2
public class JwtService {
    private static final String secretKEY = "123465789";
    private static final long EXPIRE_TIME = 86400000000L;
//    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());
    public String createToken(Authentication authentication) {
        UserPrinciple user = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKEY)
                .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getAccountFromJwtToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }
}
