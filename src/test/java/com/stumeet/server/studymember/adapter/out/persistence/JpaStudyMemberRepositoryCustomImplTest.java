package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.template.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JpaStudyMemberRepositoryCustomImplTest extends IntegrationTest {

    @Autowired
    private JpaStudyMemberRepositoryCustomImpl studyMemberRepositoryCustom;


    @Nested
    @DisplayName("스터디 멤버 조회")
    class FindStudyMembers {

        @Test
        @DisplayName("[성공] 스터디 멤버를 조회한다.")
        void successTest() {
            int want = 2;

            List<SimpleStudyMemberResponse> got = studyMemberRepositoryCustom.findStudyMembersByStudyId(StudyStub.getStudyId());

            assertThat(got).hasSize(want);
        }

    }

    @Nested
    @DisplayName("스터디에 참여한 멤버인지 여부 확인")
    class IsStudyJoinMember {

        @Test
        @DisplayName("[성공] 스터디에 참여한 멤버라면 true를 반환한다.")
        void studyJoinMemberSuccessTest() {
            boolean want = true;
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            boolean got = studyMemberRepositoryCustom.isStudyJoinMember(studyId, memberId);

            assertThat(got).isEqualTo(want);
        }

        @Test
        @DisplayName("[성공] 스터디에 참여한 멤버가 아니라면 false를 반환한다.")
        void notStudyJoinMemberTest() {
            boolean want = false;
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getInvalidMemberId();

            boolean got = studyMemberRepositoryCustom.isStudyJoinMember(studyId, memberId);

            assertThat(got).isEqualTo(want);
        }
    }
}