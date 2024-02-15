package com.stumeet.server.common.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthenticationHeader {
    ACCESS_TOKEN("Authorization"),
    X_OAUTH_PROVIDER("X-OAUTH-PROVIDER");

    private final String name;
}
