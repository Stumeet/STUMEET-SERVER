package com.stumeet.server.common.client.oauth.apple.model;

import java.util.List;

public record ApplePublicKeyResponses(
        List<ApplePublicKeyResponse> keys

) {
    public record ApplePublicKeyResponse(
            String kid,
            String n,
            String e
    ) {
    }

}
