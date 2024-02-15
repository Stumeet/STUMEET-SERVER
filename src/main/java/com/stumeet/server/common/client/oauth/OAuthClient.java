package com.stumeet.server.common.client.oauth;

import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;

public interface OAuthClient {
    OAuthUserProfileResponse getMyProfile(String accessToken);
}
