package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.adapter.out.persistence.entity.StudyMemberJpaEntity;
import com.stumeet.server.studymember.adapter.out.persistence.mapper.StudyMemberPersistenceMapper;
import com.stumeet.server.studymember.adapter.out.persistence.repository.JpaStudyMemberRepository;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.application.port.out.StudyMemberUpdatePort;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;
import com.stumeet.server.studymember.domain.StudyMember;
import com.stumeet.server.studymember.domain.exception.StudyMemberNotExistException;

import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyMemberPersistenceAdapter
    implements StudyMemberJoinPort, StudyMemberQueryPort, StudyMemberValidationPort,
    StudyMemberUpdatePort {

    private final JpaStudyMemberRepository jpaStudyMemberRepository;
    private final StudyMemberPersistenceMapper studyMemberPersistenceMapper;

    @Override
    public List<SimpleStudyMemberResponse> findStudyMembers(Long studyId) {
        return jpaStudyMemberRepository.findStudyMembersByStudyId(studyId);
    }

    @Override
    public StudyMember findStudyMember(Long studyId, Long memberId) {
        StudyMemberJpaEntity entity = jpaStudyMemberRepository.findStudyMemberByStudyIdAndMemberId(studyId, memberId)
            .orElseThrow(() -> new StudyMemberNotExistException(memberId));

        return studyMemberPersistenceMapper.toDomain(entity);
    }

    @Override
    public StudyMember findStudyMember(Long studyMemberId) {
        StudyMemberJpaEntity entity = jpaStudyMemberRepository.findById(studyMemberId)
            .orElseThrow(() -> new StudyMemberNotExistException(studyMemberId));

        return studyMemberPersistenceMapper.toDomain(entity);
    }

    @Override
    public void join(StudyMember studyMember) {
        jpaStudyMemberRepository.save(studyMemberPersistenceMapper.toEntity(studyMember));
    }

    @Override
    public void update(StudyMember studyMember) {
        StudyMemberJpaEntity entity = studyMemberPersistenceMapper.toEntity(studyMember);
        jpaStudyMemberRepository.save(entity);
    }

    @Override
    public boolean isSentGrape(Long studyId, Long memberId) {
        return jpaStudyMemberRepository.isSentGrape(studyId, memberId);
    }

    @Override
    public boolean isExist(Long id) {
        return jpaStudyMemberRepository.existsById(id);
    }

    @Override
    public boolean isNotStudyJoinMember(Long studyId, Long memberId) {
        return !jpaStudyMemberRepository.isStudyJoinMember(studyId, memberId);
    }

    @Override
    public boolean isAlreadyStudyJoinMember(Long studyId, Long memberId) {
        return jpaStudyMemberRepository.isStudyJoinMember(studyId, memberId);
    }

    @Override
    public boolean isAdmin(Long studyId, Long adminId) {
        return jpaStudyMemberRepository.isAdmin(studyId, adminId);
    }
}
