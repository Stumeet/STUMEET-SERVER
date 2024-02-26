package com.stumeet.server.common.client.oauth.kakao;

import com.stumeet.server.common.client.oauth.kakao.model.KakaoUserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoOAuthClient", url = "${oauth.client.kakao.url}")
public interface KakaoOAuthFeignClient {

    @GetMapping("/v1/user/access_token_info")
    ResponseEntity<KakaoUserProfileResponse> getUserId(
            @RequestHeader("Authorization") String accessToken
    );
}
