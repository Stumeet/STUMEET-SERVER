package com.stumeet.server.activity.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.activity.application.port.in.ActivityModifyUseCase;
import com.stumeet.server.activity.application.port.in.command.ActivityModifyCommand;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityModifyApi {

    private final ActivityModifyUseCase activityModifyUseCase;

    @PatchMapping("/studies/{studyId}/activities/{activityId}")
    public ResponseEntity<ApiResponse<Void>> modify(
            @AuthenticationPrincipal LoginMember loginMember,
            @PathVariable Long studyId,
            @PathVariable Long activityId,
            @RequestBody @Valid ActivityModifyCommand command
    ) {
        activityModifyUseCase.modify(loginMember.getId(), studyId, activityId, command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.UPDATE_SUCCESS));
    }
}
