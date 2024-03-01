package test.testamazon.persistance.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import test.testamazon.persistance.entities.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
