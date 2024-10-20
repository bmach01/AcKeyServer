package org.bmach01.AcKeyAPI.service;

import org.bmach01.AcKeyAPI.dao.AccessKeyRepository;
import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.accessKey.AccessKey;
import org.bmach01.AcKeyAPI.domain.response.KeyResponse;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AccessKeyService {

    private final JwtService jwtService;
    private final AccessKeyRepository keyRepository;
    private final UserRepository userRepository;
    private static final String bearerPrefix = "Bearer ";
    private static final int KEY_LENGTH = 64;

    public AccessKeyService(JwtService jwtService, AccessKeyRepository keyRepository, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
    }

    public AccessKey generateKey(String header) {
        String username = jwtService.extractUsername(header.substring(bearerPrefix.length()));
        User user = userRepository.findByUsername(username).orElseThrow();

        AccessKey accessKey = new AccessKey();
        accessKey.setUserId(user.getId());
        accessKey.setKey(generateRandomCode());
        accessKey.setGeneratedOn(new Date());
        accessKey.setValidUntil(new Date(System.currentTimeMillis() + 60 * 1000));

        keyRepository.save(accessKey);

        return accessKey;
    }

    private String generateRandomCode() {
        int left = 48; // '0'
        int right = 122; // 'z'

        Random random = new Random();

        return random.ints(left, right + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(KEY_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
