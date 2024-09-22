package org.bmach01.AcKeyAPI.domain.response;

import org.bmach01.AcKeyAPI.domain.accessKey.AccessKey;

public record KeyResponse(
    String message,
    AccessKey key
) {
}
