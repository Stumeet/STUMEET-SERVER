package com.stumeet.server.study.adapter.out.persistance;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.domain.Study;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyPersistenceAdapter implements StudyQueryPort {

	private final StudyRepository studyRepository;
	private final StudyPersistenceMapper studyPersistenceMapper;

	@Override
	public Study getById(Long id) {
		StudyJpaEntity entity = studyRepository.findById(id);
		return studyPersistenceMapper.toDomain(entity);
	}
}
