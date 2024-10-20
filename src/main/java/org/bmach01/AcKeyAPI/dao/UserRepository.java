package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.user.AccountStatus;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndStatus(String id, AccountStatus status);
}
