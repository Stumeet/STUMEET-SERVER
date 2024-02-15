package com.stumeet.server.common.client.oauth.kakao;

import com.stumeet.server.common.client.oauth.kakao.model.KakaoUserProfileResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoOAuthClient", url = "https://kapi.kakao.com")
public interface KakaoOAuthFeignClient {

    @PostMapping("/v2/user/me")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<KakaoUserProfileResponse> getMyProfile(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody String propertyKey
    );
}
