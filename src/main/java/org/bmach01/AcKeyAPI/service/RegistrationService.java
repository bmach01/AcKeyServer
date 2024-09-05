package org.bmach01.AcKeyAPI.service;

import org.bmach01.AcKeyAPI.domain.activationOTP.ActivationOTP;
import org.bmach01.AcKeyAPI.domain.user.User;

import java.util.UUID;

public class RegistrationService {

    public RegistrationService() {}

    private UUID getUUID() {
        return UUID.randomUUID();
    }

//    public User registerUser(User user, ActivationOTP otp) {
//        // Takes in user and activationOTP
//        // Registers the device
//        // Return UUID?
//    }

}
