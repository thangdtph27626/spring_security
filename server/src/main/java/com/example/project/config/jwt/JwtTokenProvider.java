package com.example.project.config.jwt;

import com.example.project.entity.Users;
import com.example.project.model.UserDetailCustom;
import com.example.project.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author thangdt
 */
@Component
@Slf4j
@Transactional
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết hoặc Keys.secretKeyFor(SignatureAlgorithm.HS512);
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    @Autowired
    private UserRepository userRepository;

    // Tạo ra jwt từ thông tin user
    public String generateToken(UserDetailCustom user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        Users user1 = userRepository.findByEmail(user.getUsername()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user1.getId());
        claims.put("email", user1.getEmail());
        claims.put("roles", user1.getRole());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }


    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("id"));
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
