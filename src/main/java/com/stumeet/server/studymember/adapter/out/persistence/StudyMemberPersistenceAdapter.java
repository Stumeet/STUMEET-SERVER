package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.adapter.out.persistence.mapper.StudyMemberPersistenceMapper;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
import com.stumeet.server.studymember.domain.StudyMember;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyMemberPersistenceAdapter implements StudyMemberJoinPort {

    private final JpaStudyMemberRepository jpaStudyMemberRepository;
    private final StudyMemberPersistenceMapper studyMemberPersistenceMapper;

    @Override
    public void join(StudyMember studyMember) {
        jpaStudyMemberRepository.save(studyMemberPersistenceMapper.toEntity(studyMember));
    }
}
