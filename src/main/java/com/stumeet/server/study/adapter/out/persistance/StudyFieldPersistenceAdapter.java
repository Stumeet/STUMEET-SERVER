package com.stumeet.server.study.adapter.out.persistance;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyFieldJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyFieldPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyFieldQueryPort;
import com.stumeet.server.study.domain.StudyField;
import com.stumeet.server.study.domain.exception.StudyFieldNotExistsException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyFieldPersistenceAdapter implements StudyFieldQueryPort {

	private final JpaStudyFieldRepository studyFieldRepository;
	private final StudyFieldPersistenceMapper studyFieldPersistenceMapper;

	@Override
	public void checkById(Long id) {
		if (!studyFieldRepository.existsById(id)) {
			throw new StudyFieldNotExistsException(id);
		}
	}

	@Override
	public StudyField getById(Long id) {
		StudyFieldJpaEntity entity = studyFieldRepository.findById(id)
				.orElseThrow(() -> new StudyFieldNotExistsException(id));

		return studyFieldPersistenceMapper.toDomain(entity);
	}
}
