package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.activity.application.port.out.ActivityCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityImageCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCreatePort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.domain.exception.NotStudyAdminException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

class ActivityCreateServiceTest extends UnitTest {

    @InjectMocks
    private ActivityCreateService activityCreateService;

    @Mock
    private ActivityCreatePort activityCreatePort;

    @Mock
    private ActivityImageCreatePort activityImageCreatePort;

    @Mock
    private ActivityParticipantCreatePort activityParticipantPort;

    @Mock
    private StudyMemberValidationUseCase studyMemberValidationUseCase;

    @Mock
    private ActivityUseCaseMapper activityUseCaseMapper;


    @Mock
    private ActivityImageUseCaseMapper activityImageUseCaseMapper;

    @Mock
    private ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;

    @Mock
    private StudyValidationUseCase studyValidationUseCase;

    @Nested
    @DisplayName("활동 생성")
    class Create {

        @Test
        @DisplayName("[성공] 활동 생성에 성공한다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();
            Activity activity = ActivityStub.getDefaultActivity();

            given(activityUseCaseMapper.toConstructCommand(any(), any(), any()))
                    .willReturn(ActivityStub.getDefaultConstructCommand());
            given(activityCreatePort.create(any()))
                    .willReturn(ActivityStub.getDefaultActivity());
            given(activityImageUseCaseMapper.toDomains(any(), any()))
                    .willReturn(ActivityStub.getActivityImages(activity));
            given(activityParticipantUseCaseMapper.toDomains(any(), any()))
                    .willReturn(ActivityStub.getActivityParticipants(activity));

            activityCreateService.create(studyId, request, memberId);

            then(activityCreatePort).should().create(any());
            then(activityImageCreatePort).should().create(any());
            then(activityParticipantPort).should().create(any());
        }

        @Test
        @DisplayName("[실패] 존재하지 않는 스터디로 활동 생성 요청 시 예외가 발생한다.")
        void notExistsStudyTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getInvalidMemberId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            willThrow(new StudyNotExistsException(studyId))
                    .given(studyValidationUseCase).checkById(studyId);

            assertThatCode(() -> activityCreateService.create(studyId, request, memberId))
                    .isInstanceOf(StudyNotExistsException.class)
                    .hasMessage(MessageFormat.format(StudyNotExistsException.MESSAGE, studyId));
        }

        @Test
        @DisplayName("[실패] 생성 요청을 한 사용자가 스터디의 관리자가 아닌 경우 예외가 발생한다.")
        void notAdminTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getInvalidMemberId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            willThrow(new NotStudyAdminException(studyId, memberId))
                    .given(studyMemberValidationUseCase).checkAdmin(studyId, memberId);

            assertThatCode(() -> activityCreateService.create(studyId, request, memberId))
                    .isInstanceOf(NotStudyAdminException.class)
                    .hasMessage(MessageFormat.format(NotStudyAdminException.MESSAGE, studyId, memberId));

        }

    }
}