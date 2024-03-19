package com.stumeet.server.studymember.application.service;

import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyMemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.in.response.StudyMemberResponses;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotJoinedException;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;


class StudyMemberQueryServiceTest extends UnitTest {

    @InjectMocks
    private StudyMemberQueryService studyMemberQueryService;

    @Mock
    private StudyMemberValidationUseCase studyMemberValidationUseCase;

    @Mock
    private StudyMemberQueryPort studyMemberQueryPort;


    @Nested
    @DisplayName("스터디 멤버 조회")
    class GetStudyMembers {

        @Test
        @DisplayName("[성공] 스터디 멤버를 조회할 수 있다.")
        void successTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();
            List<SimpleStudyMemberResponse> want = List.of(StudyMemberStub.getSimpleStudyMemberResponse());

            given(studyMemberQueryPort.findStudyMembers(studyId)).willReturn(want);

            StudyMemberResponses got = studyMemberQueryService.getStudyMembers(studyId, memberId);

            assertThat(got.studyMembers()).usingRecursiveComparison().isEqualTo(want);
        }

        @Test
        @DisplayName("[실패] 요청한 멤버가 스터디 멤버가 아니라면 예외가 발생한다.")
        void notJoinedMemberTest() {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            willThrow(StudyMemberNotJoinedException.class)
                    .given(studyMemberValidationUseCase).checkStudyJoinMember(studyId, memberId);

            assertThatCode(() -> studyMemberQueryService.getStudyMembers(studyId, memberId))
                    .isInstanceOf(StudyMemberNotJoinedException.class);
        }
    }
}