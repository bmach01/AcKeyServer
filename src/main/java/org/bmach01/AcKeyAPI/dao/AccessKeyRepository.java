package org.bmach01.AcKeyAPI.dao;

import org.bmach01.AcKeyAPI.domain.AccessKey.AccessKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessKeyRepository extends MongoRepository<AccessKey, String> {
}