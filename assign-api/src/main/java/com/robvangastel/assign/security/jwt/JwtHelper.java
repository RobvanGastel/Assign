package com.robvangastel.assign.security.jwt;

import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.security.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Rob van Gastel
 */

@ApplicationScoped
public class JwtHelper {

    private final String secret = "overheid";

    // Current experation date is 1 day
    private final long expirationInSeconds = 3600 * 24;

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expirationInSeconds * 1000);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getEmail());
        claims.put("role", user.getRole().name());

        return generateTokenFromClaims(claims);
    }

    public UserPrincipal parseToken(String token) throws Exception {
        Claims claims = extractClaimsFromToken(token);
        if (claimsIsExpired(claims)) {
            throw new RuntimeException("token is expired");
        }
        return new JwtUser((String) claims.get("sub"), Arrays.asList(Role.valueOf((String) claims.get("role"))));
    }

    private boolean claimsIsExpired(Claims claims) throws Exception {
        boolean isExpired = claims.getExpiration().before(new Date());
        return isExpired;
    }

    public String refreshToken(String token) throws Exception {
        Claims claims = extractClaimsFromToken(token);

        if (claimsIsExpired(claims)) {
            return generateTokenFromClaims(claims);
        }
        return token;
    }

    private String generateTokenFromClaims(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    private Claims extractClaimsFromToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("token:" + token + " is invalid");
        }
        return claims;
    }

}
