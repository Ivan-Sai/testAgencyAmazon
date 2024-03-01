package test.testamazon.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import test.testamazon.persistance.entities.user.UserEntity;

public interface JwtService {

    String generateToken(UserEntity user);

    String generateRefreshToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}