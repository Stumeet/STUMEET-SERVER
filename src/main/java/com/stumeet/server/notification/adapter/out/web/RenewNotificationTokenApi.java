package com.stumeet.server.notification.adapter.out.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.notification.adapter.out.web.dto.RenewNotificationTokenRequest;
import com.stumeet.server.notification.application.port.in.RenewNotificationTokenUseCase;
import com.stumeet.server.notification.application.port.in.command.RenewNotificationTokenCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RenewNotificationTokenApi {

    private final RenewNotificationTokenUseCase renewNotificationTokenUseCase;

    @PostMapping("/notification-token/renew")
    public ResponseEntity<ApiResponse<Void>> renewRegistrationToken(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody @Valid RenewNotificationTokenRequest request
    ) {
        RenewNotificationTokenCommand command = new RenewNotificationTokenCommand(
                member.getId(),
                request.deviceId(),
                request.notificationToken()
        );

        renewNotificationTokenUseCase.renewNotificationToken(command);

        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "알림 토큰이 갱신되었습니다."));
    }
}
