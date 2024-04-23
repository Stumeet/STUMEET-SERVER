package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.application.port.out.StudyMemberLeavePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyMemberLeavePersistenceAdapter implements StudyMemberLeavePort {
    private final JpaStudyMemberRepository jpaStudyMemberRepository;

    @Override
    public void leave(Long studyId, Long memberId) {
        jpaStudyMemberRepository.deleteByStudyIdAndMemberId(studyId, memberId);
    }

    @Override
    public void removeAllStudyMember(Long studyId) {
        jpaStudyMemberRepository.deleteAllByStudyId(studyId);
    }
}
