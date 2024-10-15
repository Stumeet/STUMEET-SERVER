package com.stumeet.server.notification.adapter.out.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RenewNotificationTokenRequest(
        @NotBlank(message = "디바이스 식별자는 공백일 수 없습니다.")
        String deviceId,

        @NotBlank(message = "알림 토큰은 공백일 수 없습니다.")
        String notificationToken
) {
}
