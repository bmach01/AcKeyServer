package org.bmach01.AcKeyAPI.domain.user;

import org.bmach01.AcKeyAPI.domain.user.AccountStatus;

public record User(
        String id,
        String name,
        String password,
        String device,
        AccountStatus status
) {
}
