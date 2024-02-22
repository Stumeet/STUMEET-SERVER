package com.stumeet.server.common.client.oauth.apple;

import com.stumeet.server.common.client.oauth.OAuthClient;
import com.stumeet.server.common.client.oauth.apple.model.ApplePublicKeyResponses;
import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import com.stumeet.server.common.util.JwtUtil;
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
        String parseAccessToken = JwtUtil.resolveToken(accessToken);
        PublicKey publicKey = appleIdTokenProvider.getSecretKey(publicKeys, parseAccessToken);
        String id = appleIdTokenProvider.extractUserId(publicKey, parseAccessToken);

        return new OAuthUserProfileResponse(id);
    }
}
