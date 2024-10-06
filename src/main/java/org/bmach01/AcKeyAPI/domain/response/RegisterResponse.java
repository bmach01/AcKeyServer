package org.bmach01.AcKeyAPI.domain.response;

public record RegisterResponse(
        String message,
        String username,
        String password
) {
}
