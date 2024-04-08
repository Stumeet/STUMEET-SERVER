package com.stumeet.server.study.adapter.out.persistance;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyDomainJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyDomainPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyDomainCommandPort;
import com.stumeet.server.study.domain.StudyDomain;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyDomainPersistenceAdapter implements StudyDomainCommandPort {

	private final JpaStudyDomainRepository studyDomainRepository;
	private final StudyDomainPersistenceMapper studyDomainPersistenceMapper;

	@Override
	public StudyDomain save(StudyDomain studyDomain) {
		StudyDomainJpaEntity entity = studyDomainRepository.save(studyDomainPersistenceMapper.toEntity(studyDomain));

		return studyDomainPersistenceMapper.toDomain(entity);
	}
}
