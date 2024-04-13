package com.stumeet.server.studymember.application.service;

import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.member.domain.exception.MemberNotExistsException;
import com.stumeet.server.stub.StudyMemberStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.application.port.in.mapper.StudyMemberUseCaseMapper;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
import com.stumeet.server.studymember.domain.exception.AlreadyStudyJoinMemberException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


class StudyMemberJoinServiceTest extends UnitTest {

    @InjectMocks
    private StudyMemberJoinService studyMemberJoinService;

    @Mock
    private MemberValidationUseCase memberValidationUseCase;

    @Mock
    private StudyValidationUseCase studyValidationUseCase;

    @Mock
    private StudyMemberUseCaseMapper studyMemberUseCaseMapper;

    @Mock
    private StudyMemberJoinPort studyMemberJoinPort;

    @Mock
    private StudyMemberValidationUseCase studyMemberValidationUseCase;

    @Nested
    @DisplayName("[단위테스트] 스터디 가입")
    class Join {

        @Test
        @DisplayName("[성공] 스터디 가입")
        void successTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();
            given(studyMemberUseCaseMapper.toDomain(command)).willReturn(StudyMemberStub.getStudyMember());

            studyMemberJoinService.join(command);

            verifyMethodCall();
        }

        private void verifyMethodCall() {
            then(memberValidationUseCase).should().checkById(any());
            then(studyValidationUseCase).should().checkById(any());
            then(studyMemberValidationUseCase).should().checkAlreadyStudyJoinMember(any(), any());
            then(studyMemberUseCaseMapper).should().toDomain(any());
            then(studyMemberJoinPort).should().join(any());
        }

        @Test
        @DisplayName("[실패] 멤버가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsMemberTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();

            willThrow(MemberNotExistsException.class)
                    .given(memberValidationUseCase).checkById(command.memberId());

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(MemberNotExistsException.class);

        }

        @Test
        @DisplayName("[실패] 스터디가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsStudyTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();

            willThrow(StudyNotExistsException.class)
                    .given(studyValidationUseCase).checkById(command.studyId());

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(StudyNotExistsException.class);}
    }

    @Test
    @DisplayName("[실패] 이미 가입한 멤버가 가입을 시도할 경우 예외 발생")
    void alreadyJoinTest() {
        StudyMemberJoinCommand command = StudyMemberStub.getAlreadyJoinCommand();

        willThrow(AlreadyStudyJoinMemberException.class)
                .given(studyMemberValidationUseCase).checkAlreadyStudyJoinMember(command.studyId(), command.memberId());

        assertThatCode(() -> studyMemberJoinService.join(command))
                .isInstanceOf(AlreadyStudyJoinMemberException.class);
    }
}