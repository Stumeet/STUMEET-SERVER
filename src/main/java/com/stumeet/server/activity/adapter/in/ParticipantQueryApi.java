package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponses;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQueryUseCase;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParticipantQueryApi {

    private final ActivityParticipantQueryUseCase activityParticipantQueryUseCase;

    @GetMapping("/studies/{studyId}/activities/{activityId}/members")
    public ResponseEntity<ApiResponse<ActivityParticipantStatusResponses>> findAllByActivityId(
            @PathVariable Long studyId,
            @PathVariable Long activityId
    ) {
        ActivityParticipantStatusResponses response = activityParticipantQueryUseCase.findAllByActivityId(studyId, activityId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
    }
}
