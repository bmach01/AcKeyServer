package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.activationCode.ActivationCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationCodeRepository extends MongoRepository<ActivationCode, String> {
    Optional<ActivationCode> findByCode(String code);
}
