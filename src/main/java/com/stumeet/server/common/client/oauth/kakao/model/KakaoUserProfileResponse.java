package com.stumeet.server.common.client.oauth.kakao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserProfileResponse(
        String id,
        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            Profile profile
    ) {
        public record Profile(
                String nickname,
                @JsonProperty("thumbnail_image_url")
                String thumbnailImageUrl
        ) {
        }
    }
}
