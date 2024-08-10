package com.ecommerce.ecommerceapi.login.jwt;

import com.ecommerce.ecommerceapi.login.security.services.UserDetailsImpl;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${ecommerce.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ecommerce.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String AuthToken){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(AuthToken);
            return true;
        } catch(MalformedJwtException me){
            logger.error("Invalid Jwt token: {}", me.getMessage());
        } catch(ExpiredJwtException ej){
            logger.error("JWT token is expired: {}", ej.getMessage());
        } catch(UnsupportedJwtException ue){
            logger.error("JWT token is unsupported: {}", ue.getMessage());
        } catch(IllegalArgumentException ie){
            logger.error("JWT claims string is empty: {}", ie.getMessage());
        }
        return false;
    }

}
