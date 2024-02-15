package com.stumeet.server.common.client.oauth.model;

public record OAuthUserProfileResponse(
    String id,
    String name,
    String imageUrl
) {
}
