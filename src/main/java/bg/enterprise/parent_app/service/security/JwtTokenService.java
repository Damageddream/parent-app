package bg.enterprise.parent_app.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenService {

    @Value("${spring.security.variables.jwt.secret}")
    private String secret; // fixme to secure place

    @Value("${spring.security.variables.jwt.expiration}")
    private long expiration;  // fixme to secure place

    private SecretKey key;

    @PostConstruct
    public void init() {
        String trimmedSecret = secret.trim();
        byte[] decodedKey = Base64.getDecoder().decode(trimmedSecret);
        this.key = Keys.hmacShaKeyFor(decodedKey);
        System.out.println("Key length in bytes: " + key.getEncoded().length);
    }

    public String generateToken(String username) {
        log.debug("Generating JWT token for user: {}", username);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        log.debug("Validating JWT token: {}", token);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            log.info("Invalid JWT token: {}", token);
            return false;
        }
    }
}