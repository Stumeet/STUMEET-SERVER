package com.stumeet.server.study.adapter.out.persistance.mapper;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.domain.StudyDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyDomainPersistenceMapper {

    private final StudyTagPersistenceMapper studyTagPersistenceMapper;

    public StudyDomain toDomain(StudyJpaEntity entity) {
        return StudyDomain.builder()
                .studyField(entity.getStudyField())
                .studyTags(studyTagPersistenceMapper.toDomains(entity.getStudyTags()))
                .build();

    }
}