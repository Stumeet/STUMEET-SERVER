package com.stumeet.server.studymember.application.service;

import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotJoinedException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;


class StudyMemberValidationServiceTest extends UnitTest {

    @InjectMocks
    private StudyMemberValidationService studyMemberValidationService;

    @Mock
    private StudyMemberValidationPort studyMemberValidationPort;

    @Nested
    @DisplayName("해당 멤버가 스터디 멤버인지 검증")
    class CheckStudyJoinMember {

        @Test
        @DisplayName("[성공] 해당 멤버가 스터디에 속해있으면 성공한다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            given(studyMemberValidationPort.isNotStudyJoinMember(studyId, memberId)).willReturn(false);

            studyMemberValidationService.checkStudyJoinMember(studyId, memberId);
        }

        @Test
        @DisplayName("[실패] 해당 멤버가 스터디에 속해있지 않으면 예외가 발생한다.")
        void notMatchedTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            given(studyMemberValidationPort.isNotStudyJoinMember(studyId, memberId)).willReturn(true);

            assertThatCode(() -> studyMemberValidationService.checkStudyJoinMember(studyId, memberId))
                    .isInstanceOf(StudyMemberNotJoinedException.class);
        }
    }
}