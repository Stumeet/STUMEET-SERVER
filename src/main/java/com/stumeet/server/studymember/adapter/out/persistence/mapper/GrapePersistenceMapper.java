package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.stumeet.server.studymember.adapter.out.persistence.entity.GrapeJpaEntity;
import com.stumeet.server.studymember.adapter.out.persistence.entity.LinkedStudyJpaEntity;
import com.stumeet.server.studymember.domain.Grape;

@Component
public class GrapePersistenceMapper {

    public GrapeJpaEntity toEntity(Grape grape) {
        return GrapeJpaEntity.builder()
            .id(grape.getId())
            .memberId(grape.getMemberId())
            .linkedStudy(LinkedStudyJpaEntity.builder()
                .id(grape.getStudyId())
                .name(grape.getStudyName())
                .build())
            .complimentType(grape.getComplimentType())
            .build();
    }
}
