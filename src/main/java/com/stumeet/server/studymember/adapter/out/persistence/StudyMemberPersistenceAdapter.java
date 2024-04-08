package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.adapter.out.persistence.mapper.StudyMemberPersistenceMapper;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;
import com.stumeet.server.studymember.domain.StudyMember;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyMemberPersistenceAdapter implements StudyMemberJoinPort, StudyMemberQueryPort, StudyMemberValidationPort {

    private final JpaStudyMemberRepository jpaStudyMemberRepository;
    private final StudyMemberPersistenceMapper studyMemberPersistenceMapper;

    @Override
    public void join(StudyMember studyMember) {
        jpaStudyMemberRepository.save(studyMemberPersistenceMapper.toEntity(studyMember));
    }

    @Override
    public List<SimpleStudyMemberResponse> findStudyMembers(Long studyId) {
        return jpaStudyMemberRepository.findStudyMembersByStudyId(studyId);
    }

    @Override
    public boolean isNotStudyJoinMember(Long studyId, Long memberId) {
        return !jpaStudyMemberRepository.isStudyJoinMember(studyId, memberId);
    }

    @Override
    public boolean isNotAdmin(Long studyId, Long adminId) {
        return !jpaStudyMemberRepository.isAdmin(studyId, adminId);
    }
}
