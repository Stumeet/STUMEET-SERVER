package com.stumeet.server.bff.adapter.in.app;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponse;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.activity.application.port.in.query.ActivityListDetailedQuery;
import com.stumeet.server.bff.adapter.in.app.response.StudyDetailFullResponse;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.study.adapter.in.web.response.StudyDetailResponse;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberQueryUseCase;
import com.stumeet.server.studymember.application.port.in.response.MyStudyMemberDetailResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/external/v1")
@RequiredArgsConstructor
public class StudyHubApi {

    private final StudyQueryUseCase studyQueryUseCase;
    private final StudyMemberQueryUseCase studyMemberQueryUseCase;
    private final ActivityQueryUseCase activityQueryUseCase;

    @GetMapping("/studies/{studyId}")
    public ResponseEntity<ApiResponse<StudyDetailFullResponse>> getStudyDetailFull(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "studyId") Long studyId
    ) {
        StudyDetailResponse studyDetailResponse = studyQueryUseCase.getStudyDetailById(studyId);

        ActivityListDetailedQuery noticeQuery = getNoticeQuery(member.getId(), studyId);
        ActivityListDetailedPageResponse activityNotice = activityQueryUseCase.getDetails(noticeQuery)
                .items()
                .getFirst();
        MyStudyMemberDetailResponse myStudyMemberDetail = studyMemberQueryUseCase.getMyStudyMemberDetail(studyId, member.getId());


        StudyDetailFullResponse response = new StudyDetailFullResponse(
                studyDetailResponse,
                activityNotice,
                myStudyMemberDetail.isAdmin(),
                myStudyMemberDetail.canSendGrape()
        );

        return ResponseEntity.ok(
                ApiResponse.success(SuccessCode.GET_SUCCESS, response)
        );
    }

    private ActivityListDetailedQuery getNoticeQuery(Long memberId, Long studyId) {
        return ActivityListDetailedQuery.builder()
                .memberId(memberId)
                .studyId(studyId)
                .page(0)
                .size(1)
                .isNotice(true)
                .build();
    }
}
