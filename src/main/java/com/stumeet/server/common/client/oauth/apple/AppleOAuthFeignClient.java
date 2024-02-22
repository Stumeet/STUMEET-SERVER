package com.stumeet.server.common.client.oauth.apple;

import com.stumeet.server.common.client.oauth.apple.model.ApplePublicKeyResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appleOAuthClient", url = "https://appleid.apple.com")
public interface AppleOAuthFeignClient {

    @GetMapping("/auth/keys")
    ApplePublicKeyResponses getPublicKeys();

}
