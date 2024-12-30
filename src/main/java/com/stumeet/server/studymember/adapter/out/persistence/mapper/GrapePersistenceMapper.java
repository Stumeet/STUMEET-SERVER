package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.stumeet.server.studymember.adapter.out.persistence.entity.GrapeJpaEntity;
import com.stumeet.server.studymember.adapter.out.persistence.entity.LinkedStudyJpaEntity;
import com.stumeet.server.studymember.domain.Grape;

@Component
public class GrapePersistenceMapper {

    public GrapeJpaEntity toEntity(Grape domain) {
        return GrapeJpaEntity.builder()
            .id(domain.getId())
            .memberId(domain.getMemberId())
            .linkedStudy(LinkedStudyJpaEntity.builder()
                .id(domain.getStudyId())
                .name(domain.getStudyName())
                .build())
            .complimentType(domain.getComplimentType())
            .build();
    }

    public Grape toDomain(GrapeJpaEntity entity) {
        return Grape.builder()
            .id(entity.getId())
            .memberId(entity.getMemberId())
            .studyId(entity.getLinkedStudy().getId())
            .studyName(entity.getLinkedStudy().getName())
            .complimentType(entity.getComplimentType())
            .createdAt(entity.getCreatedAt())
            .build();
    }

    public Page<Grape> toDomains(Page<GrapeJpaEntity> entities) {
        return entities.map(this::toDomain);
    }
}
