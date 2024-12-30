package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.studymember.adapter.out.persistence.entity.GrapeJpaEntity;
import com.stumeet.server.studymember.adapter.out.persistence.mapper.GrapePersistenceMapper;
import com.stumeet.server.review.application.port.out.GrapeSavePort;
import com.stumeet.server.studymember.adapter.out.persistence.repository.JpaGrapeRepository;
import com.stumeet.server.studymember.domain.Grape;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class GrapePersistenceAdapter implements GrapeSavePort {
    private final JpaGrapeRepository jpaGrapeRepository;
    private final GrapePersistenceMapper grapePersistenceMapper;

    @Override
    public void save(Grape grape) {
        GrapeJpaEntity entity = grapePersistenceMapper.toEntity(grape);
        jpaGrapeRepository.save(entity);
    }
}
