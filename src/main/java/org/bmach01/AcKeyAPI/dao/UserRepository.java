package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


}
