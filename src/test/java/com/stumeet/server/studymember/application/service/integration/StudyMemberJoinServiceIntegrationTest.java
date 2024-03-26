package com.stumeet.server.studymember.application.service.integration;

import com.stumeet.server.member.domain.exception.MemberNotExistsException;
import com.stumeet.server.stub.StudyMemberStub;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import com.stumeet.server.studymember.adapter.out.persistence.JpaStudyMemberRepository;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.application.service.StudyMemberJoinService;
import com.stumeet.server.template.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class StudyMemberJoinServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StudyMemberJoinService studyMemberJoinService;

    @Autowired
    private JpaStudyMemberRepository jpaStudyMemberRepository;

    @Nested
    @DisplayName("[통합 테스트] 스터디 가입")
    class Join {

        @Test
        @DisplayName("[성공] 스터디 가입")
        void successTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getJoinCommand();
            long want = jpaStudyMemberRepository.countByStudyId(command.studyId()) + 1;

            studyMemberJoinService.join(command);

            long studyMemberCount = jpaStudyMemberRepository.countByStudyId(command.studyId());

            assertThat(studyMemberCount).isEqualTo(want);
        }

        @Test
        @DisplayName("[실패] 멤버가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsMemberTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getInvalidJoinCommandByMemberId();

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(MemberNotExistsException.class);

        }

        @Test
        @DisplayName("[실패] 스터디가 실제로 존재하지 않는 ID가 전달된 경우 예외 발생")
        void notExistsStudyTest() {
            StudyMemberJoinCommand command = StudyMemberStub.getInvalidJoinCommandByStudyId();

            assertThatCode(() -> studyMemberJoinService.join(command))
                    .isInstanceOf(StudyNotExistsException.class);
        }
    }
}


