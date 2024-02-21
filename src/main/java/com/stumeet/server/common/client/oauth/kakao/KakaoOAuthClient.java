package com.stumeet.server.common.client.oauth.kakao;

import com.stumeet.server.common.client.oauth.OAuthClient;
import com.stumeet.server.common.client.oauth.kakao.model.KakaoUserProfileResponse;
import com.stumeet.server.common.client.oauth.model.OAuthUserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private final KakaoOAuthFeignClient kakaoOAuthClient;

    @Override
    public OAuthUserProfileResponse getMyProfile(String accessToken) {
        ResponseEntity<KakaoUserProfileResponse> response = kakaoOAuthClient.getUserId(accessToken);

        KakaoUserProfileResponse responseBody = response.getBody();

        return new OAuthUserProfileResponse(
                responseBody.id()
        );
    }
}
