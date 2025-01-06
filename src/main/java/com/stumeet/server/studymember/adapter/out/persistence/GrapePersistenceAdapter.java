package com.stumeet.server.studymember.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.review.application.port.out.GrapeQueryPort;
import com.stumeet.server.studymember.adapter.out.persistence.entity.GrapeJpaEntity;
import com.stumeet.server.studymember.adapter.out.persistence.mapper.GrapePersistenceMapper;
import com.stumeet.server.review.application.port.out.GrapeSavePort;
import com.stumeet.server.studymember.adapter.out.persistence.repository.JpaGrapeRepository;
import com.stumeet.server.studymember.domain.Grape;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class GrapePersistenceAdapter implements GrapeQueryPort, GrapeSavePort {
    private final JpaGrapeRepository jpaGrapeRepository;
    private final GrapePersistenceMapper grapePersistenceMapper;

    @Override
    public Page<Grape> findPageByMemberId(Long memberId, int page, int size) {
        Page<GrapeJpaEntity> entities = jpaGrapeRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId, PageRequest.of(page, size));
        return grapePersistenceMapper.toDomains(entities);
    }

    @Override
    public int countMemberGrapes(Long memberId) {
        return jpaGrapeRepository.countByMemberId(memberId);
    }

    @Override
    public void save(Grape grape) {
        GrapeJpaEntity entity = grapePersistenceMapper.toEntity(grape);
        jpaGrapeRepository.save(entity);
    }
}
