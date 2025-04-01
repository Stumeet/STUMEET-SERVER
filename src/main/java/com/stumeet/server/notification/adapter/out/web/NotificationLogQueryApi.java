package com.stumeet.server.notification.adapter.out.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.notification.adapter.out.web.dto.NotificationLogResponses;
import com.stumeet.server.notification.application.port.in.NotificationLogQueryUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NotificationLogQueryApi {
    private final NotificationLogQueryUseCase notificationLogQueryUseCase;

    @GetMapping("/notification/logs")
    public ResponseEntity<ApiResponse<NotificationLogResponses>> renewRegistrationToken(
        @AuthenticationPrincipal LoginMember member,
        @RequestParam(defaultValue = "20") Integer size,
        @RequestParam(defaultValue = "0") Integer page
    ) {
        NotificationLogResponses response =
            notificationLogQueryUseCase.getMemberNotificationLogs(member.getId(), size, page);

        return ResponseEntity.ok(
            ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }
}
