package test.testamazon.persistance.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import test.testamazon.persistance.entities.token.Token;
import test.testamazon.persistance.entities.user.UserEntity;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByUser(UserEntity user);

    Optional<Token> findByToken(String jwt);
}