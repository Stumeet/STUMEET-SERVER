package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityQueryApi {

    private final ActivityQueryUseCase activityQueryUseCase;

    @GetMapping("/studies/{studyId}/activities/{activityId}")
    public ResponseEntity<ApiResponse<ActivityDetailResponse>> getById(
            @PathVariable Long studyId,
            @PathVariable Long activityId,
            @AuthenticationPrincipal LoginMember member
    ) {
        ActivityDetailResponse response = activityQueryUseCase.getById(studyId, activityId, member.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
    }
}
