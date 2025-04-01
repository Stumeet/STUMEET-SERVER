package com.stumeet.server.activity.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.activity.application.port.in.ActivityDeleteUseCase;
import com.stumeet.server.activity.application.port.in.command.ActivityDeleteCommand;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityDeleteApi {

    private final ActivityDeleteUseCase activityDeleteUseCase;

    @DeleteMapping("/studies/{studyId}/activities/{activityId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable Long studyId,
            @PathVariable Long activityId
    ) {
        ActivityDeleteCommand command = ActivityDeleteCommand.builder()
                .memberId(member.getId())
                .studyId(studyId)
                .activityId(activityId)
                .build();
        activityDeleteUseCase.delete(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.DELETE_SUCCESS));
    }
}
