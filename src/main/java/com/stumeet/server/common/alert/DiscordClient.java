package com.stumeet.server.common.alert;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DiscordClient", url = "${discord.webhook.report.url}")
public interface DiscordClient {

    @PostMapping
    void sendMessage(@RequestBody Map<String, String> payload);
}
