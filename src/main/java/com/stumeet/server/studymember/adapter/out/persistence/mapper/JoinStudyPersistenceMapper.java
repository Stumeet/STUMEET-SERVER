package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import com.stumeet.server.studymember.adapter.out.persistence.JoinStudyJpaEntity;
import com.stumeet.server.studymember.domain.JoinStudy;
import org.springframework.stereotype.Component;

@Component
public class JoinStudyPersistenceMapper {

    public JoinStudy toDomain(JoinStudyJpaEntity entity) {
        return JoinStudy.builder()
                .id(entity.getId())
                .build();
    }

    public JoinStudyJpaEntity toEntity(JoinStudy domain) {
        return JoinStudyJpaEntity.builder()
                .id(domain.getId())
                .build();
    }
}
