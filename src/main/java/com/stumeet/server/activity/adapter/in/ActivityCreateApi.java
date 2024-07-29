package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.application.port.in.ActivityCreateUseCase;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityCreateApi {

    private final ActivityCreateUseCase activityCreateUseCase;
    private final ActivityQueryUseCase activityQueryUseCase;

    @PostMapping("/studies/{studyId}/activities")
    public ResponseEntity<ApiResponse<ActivityDetailResponse>> create(
            @PathVariable Long studyId,
            @AuthenticationPrincipal LoginMember loginMember,
            @RequestBody @Valid ActivityCreateCommand command
    ) {
        Long createdId = activityCreateUseCase.create(studyId, command, loginMember.getId());
        ActivityDetailResponse response = activityQueryUseCase.getById(studyId, createdId, loginMember.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(SuccessCode.ACTIVITY_CREATE_SUCCESS, response));
    }
}
