package org.bmach01.AcKeyAPI.service;

import org.bmach01.AcKeyAPI.dao.AccessKeyRepository;
import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.accessKey.AccessKey;
import org.bmach01.AcKeyAPI.domain.response.KeyResponse;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccessKeyService {

    private final JwtService jwtService;
    private final AccessKeyRepository keyRepository;
    private final UserRepository userRepository;
    private static final String bearerPrefix = "Bearer ";

    public AccessKeyService(JwtService jwtService, AccessKeyRepository keyRepository, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
    }

    public KeyResponse generateKey(String header) {
        String username = jwtService.extractUsername(header.substring(bearerPrefix.length()));
        User user = userRepository.findByUsername(username).orElseThrow();

        AccessKey accessKey = new AccessKey();
        accessKey.setUserId(user.getId());
        accessKey.setKey("testKey");
        accessKey.setGeneratedOn(new Date());
        accessKey.setValidUntil(new Date(System.currentTimeMillis() + 60 * 1000));

        keyRepository.save(accessKey);

        return new KeyResponse("Key successfully generated", accessKey);
    }
}
