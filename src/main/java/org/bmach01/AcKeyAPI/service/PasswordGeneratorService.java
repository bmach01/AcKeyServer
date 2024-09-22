package org.bmach01.AcKeyAPI.service;

import org.passay.CharacterData;
import org.passay.*;
import org.springframework.stereotype.Service;


@Service
public class PasswordGeneratorService {

    private static final int PASSWORD_LENGTH = 128;
    private static final Rule[] RULES = {
            new CharacterRule(EnglishCharacterData.UpperCase),
            new CharacterRule(EnglishCharacterData.LowerCase),
            new CharacterRule(EnglishCharacterData.Digit),
            new CharacterRule(new CharacterData() {
                public String getErrorCode() {
                    return "INSUFFICIENT_SPECIAL";
                }
                public String getCharacters() {
                    return "!@#$%^&*";
                }
            })
    };

    public PasswordGeneratorService() {
    }

    public String generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();

        return generator.generatePassword(PASSWORD_LENGTH, RULES);
    }
}
