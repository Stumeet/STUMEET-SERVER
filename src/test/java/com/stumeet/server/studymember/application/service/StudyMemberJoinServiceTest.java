package com.stumeet.server.studymember.application.service;

import com.stumeet.server.member.application.port.in.MemberValidUseCase;
import com.stumeet.server.member.domain.exception.MemberNotExistsException;
import com.stumeet.server.stub.StudyMemberStub;
import com.stumeet.server.study.application.port.in.StudyValidUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.application.port.in.mapper.StudyMemberUseCaseMapper;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
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
    private MemberValidUseCase memberValidUseCase;

    @Mock
    private StudyValidUseCase studyValidUseCase;

    @Mock
    private StudyMemberUseCaseMapper studyMemberUseCaseMapper;

    @Mock
    private StudyMemberJoinPort studyMemberJoinPort;

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
            then(memberValidUseCase).should().checkById(any());
            then(studyValidUseCase).should().checkById(any());
            then(studyMemberUseCaseMapper).should().toDomain(any());
            then(studyMemberJoinPort).should().join(any());
        }

        @Test
        @DisplayName("[실패] 멤버가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsMemberTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();

            willThrow(MemberNotExistsException.class)
                    .given(memberValidUseCase).checkById(command.memberId());

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(MemberNotExistsException.class);

        }

        @Test
        @DisplayName("[실패] 스터디가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsStudyTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();

            willThrow(StudyNotExistsException.class)
                    .given(studyValidUseCase).checkById(command.memberId());

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(StudyNotExistsException.class);}
    }
}