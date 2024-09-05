package org.bmach01.AcKeyAPI.domain.activationOTP;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public record ActivationOTP(
        @Id String id,
        @Field("user_id") String userId,
        String password,
        boolean used,
        LocalDateTime validUntil
) {
}
