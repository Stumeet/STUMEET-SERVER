package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.application.port.in.ActivityImageQuery;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotJoinedException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

class ActivityQueryFacadeTest extends UnitTest {

    @InjectMocks
    private ActivityQueryFacade activityQueryFacade;

    @Mock
    private ActivityQuery activityQuery;

    @Mock
    private StudyValidationUseCase studyValidationUseCase;

    @Mock
    private StudyMemberValidationUseCase studyMemberValidationUseCase;

    @Mock
    private ActivityImageQuery activityImageQuery;

    @Mock
    private ActivityParticipantQuery activityParticipantQuery;

    @Spy
    private ActivityUseCaseMapper activityUseCaseMapper = new ActivityUseCaseMapper();

    @Spy
    private ActivityImageUseCaseMapper activityImageUseCaseMapper = new ActivityImageUseCaseMapper();

    @Spy
    private ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper = new ActivityParticipantUseCaseMapper();

    @Nested
    @DisplayName("활동 단일 조회")
    class GetById {

        @Test
        @DisplayName("[성공] 스터디 활동 단일 조회에 성공합니다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();
            Long memberId = MemberStub.getMemberId();
            ActivityDetailResponse want = ActivityStub.getActivityDetailResponse();

            given(activityQuery.getById(any()))
                    .willReturn(ActivityStub.getDefaultActivity());
            given(activityImageQuery.findAllByActivityId(any()))
                    .willReturn(ActivityStub.getActivityImages(ActivityStub.getDefaultActivity()));
            given(activityParticipantQuery.findAllByActivityId(any()))
                    .willReturn(ActivityStub.getActivityParticipants(ActivityStub.getDefaultActivity()));

            ActivityDetailResponse got = activityQueryFacade.getById(studyId, activityId, memberId);

            assertThat(got).usingRecursiveComparison()
                    .ignoringFields("createdAt")
                    .isEqualTo(want);
        }

        @Test
        @DisplayName("[성공] 스터디에는 참여했지만 활동에 참여하지 않은 회원인 경우 상태가 미참여로 조회됩니다.")
        void NotJoinedActivityTest() {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();
            Long memberId = MemberStub.getNotJoinActivityMemberId();
            ActivityDetailResponse want = ActivityStub.getActivityDetailResponseForNotJoinedUser();

            given(activityQuery.getById(any()))
                    .willReturn(ActivityStub.getDefaultActivity());
            given(activityImageQuery.findAllByActivityId(any()))
                    .willReturn(ActivityStub.getActivityImages(ActivityStub.getDefaultActivity()));
            given(activityParticipantQuery.findAllByActivityId(any()))
                    .willReturn(ActivityStub.getActivityParticipants(ActivityStub.getDefaultActivity()));

            ActivityDetailResponse got = activityQueryFacade.getById(studyId, activityId, memberId);

            assertThat(got).usingRecursiveComparison()
                    .ignoringFields("createdAt")
                    .isEqualTo(want);
        }

        @Test
        @DisplayName("[실패] 존재하지 않는 스터디를 넘긴 경우 예외가 발생합니다.")
        void studyNotFoundTest() {
            Long studyId = StudyStub.getInvalidStudyId();
            Long activityId = ActivityStub.getActivityId();
            Long memberId = MemberStub.getMemberId();

            willThrow(new StudyNotExistsException(studyId))
                    .given(studyValidationUseCase).checkById(studyId);

            assertThatCode(() -> activityQueryFacade.getById(studyId, activityId, memberId))
                    .isInstanceOf(StudyNotExistsException.class)
                    .hasMessage(MessageFormat.format(StudyNotExistsException.MESSAGE, studyId));
        }

        @Test
        @DisplayName("[실패] 스터디에 참여하지 않은 회원인 경우 예외가 발생합니다.")
        void studyNotJoinedTest() {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();
            Long memberId = MemberStub.getNotJoinStudyMemberId();

            willThrow(new StudyMemberNotJoinedException(studyId, memberId))
                    .given(studyMemberValidationUseCase).checkStudyJoinMember(studyId, memberId);

            assertThatCode(() -> activityQueryFacade.getById(studyId, activityId, memberId))
                    .isInstanceOf(StudyMemberNotJoinedException.class)
                    .hasMessage(MessageFormat.format(StudyMemberNotJoinedException.MESSAGE, studyId, memberId));
        }
    }
}