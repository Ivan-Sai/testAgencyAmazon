package test.testamazon.services.security.impl;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.testamazon.api.data.AuthenticationData;
import test.testamazon.api.data.request.LoginData;
import test.testamazon.api.data.request.RegisterData;
import test.testamazon.config.security.JwtService;
import test.testamazon.exceptions.EntityNotFoundException;
import test.testamazon.exceptions.UserExistsException;
import test.testamazon.persistance.entities.token.Token;
import test.testamazon.persistance.entities.user.UserEntity;
import test.testamazon.persistance.repositories.TokenRepository;
import test.testamazon.persistance.repositories.UserRepository;
import test.testamazon.persistance.types.security.TokenType;
import test.testamazon.services.security.AuthenticationService;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationData register(RegisterData data) {
        if (userRepository.existsByUsername(data.getUsername())) {
            throw new UserExistsException("User with this username already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(data.getUsername());
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        saveToken(token, user);
        return new AuthenticationData(token);
    }

    @Override
    public AuthenticationData login(LoginData data) {
        UserEntity user = new UserEntity();
        if (userRepository.findByUsername(data.getUsername()).isPresent()) {
            user = userRepository.findByUsername(data.getUsername()).get();
        } else {

        }

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new UserExistsException("Incorrect password");
        }
        Token token = tokenRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Token not found"));
        String accessToken = token.getToken();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), data.getPassword())
        );
        try {
            jwtService.isTokenExpired(accessToken);
        } catch (ExpiredJwtException e) {
            tokenRepository.deleteById(token.getId());
            accessToken = jwtService.generateToken(user);
            saveToken(accessToken, user);
        }

        return new AuthenticationData(accessToken);
    }


    private void saveToken(String accessToken, UserEntity user) {
        Token token = new Token();
        token.setToken(accessToken);
        token.setTokenType(TokenType.BEARER);
        token.setUser(user);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }
}