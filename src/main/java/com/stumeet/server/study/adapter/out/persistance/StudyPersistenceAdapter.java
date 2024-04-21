package com.stumeet.server.study.adapter.out.persistance;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.out.StudyValidationPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyPersistenceAdapter implements StudyQueryPort, StudyValidationPort, StudyCommandPort {

	private final JpaStudyRepository studyRepository;
	private final StudyPersistenceMapper studyPersistenceMapper;

	@Override
	public Study getById(Long id) {
		StudyJpaEntity entity = studyRepository.findById(id)
				.orElseThrow(() -> new StudyNotExistsException(id));

		return studyPersistenceMapper.toDomain(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return studyRepository.existsById(id);
	}

	@Override
	public Study save(Study study) {
		StudyJpaEntity entity = studyRepository.save(studyPersistenceMapper.toEntity(study));

		return studyPersistenceMapper.toDomain(entity);
	}

	@Override
	public void delete(Long studyId) {
		studyRepository.deleteById(studyId);
	}
}
