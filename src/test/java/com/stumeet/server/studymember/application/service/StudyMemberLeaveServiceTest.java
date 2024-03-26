package com.stumeet.server.studymember.application.service;

import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberLeavePort;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotJoinedException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

class StudyMemberLeaveServiceTest extends UnitTest {

    @InjectMocks
    private StudyMemberLeaveService studyMemberLeaveService;

    @Mock
    private StudyValidationUseCase studyValidationUseCase;

    @Mock
    private StudyMemberValidationUseCase studyMemberValidationUseCase;

    @Mock
    private StudyMemberLeavePort studyMemberLeavePort;

    @Nested
    @DisplayName("스터디 멤버 탈퇴")
    class Leave {

        @Test
        @DisplayName("[성공] 스터디 멤버 탈퇴에 성공한다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            studyMemberLeaveService.leave(studyId, memberId);

            then(studyMemberLeavePort).should().leave(any(), any());
        }

        @Test
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 예외가 발생한다.")
        void studyNotFoundTest() {
            Long studyId = StudyStub.getInvalidStudyId();
            Long memberId = MemberStub.getMemberId();

            willThrow(StudyNotExistsException.class)
                    .given(studyValidationUseCase).checkById(studyId);

            assertThatCode(() -> studyMemberLeaveService.leave(studyId, memberId))
                    .isInstanceOf(StudyNotExistsException.class);
        }

        @Test
        @DisplayName("[실패] 해당 스터디에 가입한 회원이 아닌 경우 예외가 발생한다.")
        void notJoinMemberTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            willThrow(StudyMemberNotJoinedException.class)
                    .given(studyMemberValidationUseCase).checkStudyJoinMember(studyId, memberId);

            assertThatCode(() -> studyMemberLeaveService.leave(studyId, memberId))
                    .isInstanceOf(StudyMemberNotJoinedException.class);
        }
    }
}