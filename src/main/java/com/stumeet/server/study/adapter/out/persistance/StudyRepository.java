package com.stumeet.server.study.adapter.out.persistance;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;

public interface StudyRepository {

    StudyJpaEntity findById(Long id);

    boolean existsById(Long id);
}
