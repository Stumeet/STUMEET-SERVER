package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import com.stumeet.server.studymember.adapter.out.persistence.StudyMemberJpaEntity;
import com.stumeet.server.studymember.domain.StudyMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyMemberPersistenceMapper {

    private final JoinMemberPersistenceMapper memberPersistenceMapper;
    private final JoinStudyPersistenceMapper studyPersistenceMapper;

    public StudyMemberJpaEntity toEntity(StudyMember domain) {
        return StudyMemberJpaEntity.builder()
                .id(domain.getId())
                .member(memberPersistenceMapper.toEntity(domain.getMember()))
                .study(studyPersistenceMapper.toEntity(domain.getStudy()))
                .isAdmin(domain.isAdmin())
                .isSentGrape(domain.isSentGrape())
                .build();
    }

    public StudyMember toDomain(StudyMemberJpaEntity entity) {
        return StudyMember.builder()
                .id(entity.getId())
                .member(memberPersistenceMapper.toDomain(entity.getMember()))
                .study(studyPersistenceMapper.toDomain(entity.getStudy()))
                .isAdmin(entity.isAdmin())
                .isSentGrape(entity.isSentGrape())
                .build();
    }
}
