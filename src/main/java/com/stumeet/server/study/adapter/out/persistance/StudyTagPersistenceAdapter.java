package com.stumeet.server.study.adapter.out.persistance;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyTagPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;
import com.stumeet.server.study.domain.StudyTag;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyTagPersistenceAdapter implements StudyTagCommandPort {

	private final JpaStudyTagRepository studyTagRepository;
	private final StudyTagPersistenceMapper studyTagPersistenceMapper;

	@Override
	public List<StudyTag> saveAll(List<StudyTag> studyTags, Long studyDomainId) {
		List<StudyTagJpaEntity> entities =
				studyTagRepository.saveAll(studyTagPersistenceMapper.toEntities(studyTags, studyDomainId));

		return studyTagPersistenceMapper.toDomains(entities);
	}
}
