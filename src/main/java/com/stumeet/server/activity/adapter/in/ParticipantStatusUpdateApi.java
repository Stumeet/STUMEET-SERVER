package com.stumeet.server.activity.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.activity.adapter.in.request.ParticipantStatusUpdateRequest;
import com.stumeet.server.activity.application.port.in.ParticipantStatusUpdateUseCase;
import com.stumeet.server.activity.application.port.in.command.ParticipantStatusUpdateCommand;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ParticipantStatusUpdateApi {

    private final ParticipantStatusUpdateUseCase participantStatusUpdateUseCase;

    @PatchMapping("/studies/{studyId}/members/{memberId}/activities/{activityId}/status")
    public ResponseEntity<ApiResponse<Void>> updateStatus(
            @AuthenticationPrincipal LoginMember loginMember,
            @PathVariable Long studyId,
            @PathVariable Long memberId,
            @PathVariable Long activityId,
            @RequestBody @Valid ParticipantStatusUpdateRequest request
    ) {
        ParticipantStatusUpdateCommand command = ParticipantStatusUpdateCommand.builder()
                .adminId(loginMember.getId())
                .studyId(studyId)
                .activityId(activityId)
                .memberId(memberId)
                .participantId(request.participantId())
                .status(request.status())
                .build();

        participantStatusUpdateUseCase.updateStatus(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.UPDATE_SUCCESS));
    }
}
