package org.bmach01.AcKeyAPI.domain.AccessKey;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public record AccessKey(
        @Id String id,
        @Field("user_id") String userId,
        String key,
        @Field("valid_until") LocalDateTime validUntil,
        LocalDateTime used
) {
}
