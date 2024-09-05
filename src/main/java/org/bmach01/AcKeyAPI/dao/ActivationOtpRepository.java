package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.activationOTP.ActivationOtp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivationOtpRepository extends MongoRepository<ActivationOtp, String> {
    ActivationOtp findByPassword(String password);
}
