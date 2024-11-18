package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponses;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.domain.exception.NotExistsActivityException;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
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


class ActivityParticipantJpaEntityQueryFacadeTest extends UnitTest {

    @InjectMocks
    private ActivityParticipantQueryFacade activityParticipantQueryFacade;

    @Mock
    private ActivityParticipantQuery activityParticipantQuery;

    @Mock
    private StudyValidationUseCase studyValidationUseCase;

    @Mock
    private ActivityQuery activityQuery;

    @Spy
    private ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper = new ActivityParticipantUseCaseMapper();

    @Nested
    @DisplayName("활동 참여자 리스트 조회")
    class FindAllByActivityId {

        @Test
        @DisplayName("[성공] 활동 참여자 리스트를 조회한다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();
            ActivityParticipantStatusResponses want = ActivityStub.getActivityParticipantStatusResponses();

            given(activityParticipantQuery.findAllByActivityId(any()))
                    .willReturn(ActivityStub.getActivityParticipants(ActivityStub.getDefaultActivity()));

            ActivityParticipantStatusResponses got = activityParticipantQueryFacade.findAllByActivityId(studyId, activityId);

            assertThat(got).usingRecursiveComparison().isEqualTo(want);
        }

        @Test
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 예외가 발생한다.")
        void notFoundStudyTest() {
            Long invalidStudyId = StudyStub.getInvalidStudyId();
            Long activityId = ActivityStub.getActivityId();

            willThrow(new StudyNotExistsException(invalidStudyId))
                    .given(studyValidationUseCase).checkById(invalidStudyId);

            assertThatCode(() -> activityParticipantQueryFacade.findAllByActivityId(invalidStudyId, activityId))
                    .isInstanceOf(StudyNotExistsException.class)
                    .hasMessage(MessageFormat.format(StudyNotExistsException.MESSAGE, invalidStudyId));
        }

        @Test
        @DisplayName("[실패] 활동이 존재하지 않는 경우 예외가 발생합니다.")
        void notFoundActivityTest() {
            Long studyId = StudyStub.getStudyId();
            Long invalidActivityId = ActivityStub.getInvalidActivityId();

            willThrow(new NotExistsActivityException(invalidActivityId))
                    .given(activityQuery).getById(invalidActivityId);

            assertThatCode(() -> activityParticipantQueryFacade.findAllByActivityId(studyId, invalidActivityId))
                    .isInstanceOf(NotExistsActivityException.class)
                    .hasMessage(MessageFormat.format(NotExistsActivityException.MESSAGE, invalidActivityId));
        }
    }
}