package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.activationOTP.ActivationOTP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivationOTPsRepository extends MongoRepository<ActivationOTP, String> {
    ActivationOTP findByPassword(String password);
}
