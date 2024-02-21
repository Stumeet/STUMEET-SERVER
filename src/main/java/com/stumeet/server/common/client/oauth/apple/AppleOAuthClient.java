package com.stumeet.server.common.client.oauth.apple;

import com.stumeet.server.common.client.oauth.OAuthClient;
import com.stumeet.server.common.client.oauth.apple.model.ApplePublicKeyResponses;
import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component(value = "apple")
@RequiredArgsConstructor
public class AppleOAuthClient implements OAuthClient {

    private final AppleOAuthFeignClient appleOAuthClient;
    private final AppleIdTokenProvider appleIdTokenProvider;

    @Override
    public OAuthUserProfileResponse getUserId(String accessToken) {
        ApplePublicKeyResponses publicKeys = appleOAuthClient.getPublicKeys();
        PublicKey publicKey = appleIdTokenProvider.getSecretKey(publicKeys, accessToken);
        String id = appleIdTokenProvider.extractUserId(publicKey, accessToken);

        return new OAuthUserProfileResponse(id);
    }
}
