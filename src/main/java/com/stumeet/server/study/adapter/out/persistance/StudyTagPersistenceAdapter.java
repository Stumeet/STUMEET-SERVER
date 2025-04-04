package com.stumeet.server.study.adapter.out.persistance;

import java.util.List;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.mapper.StudyTagPersistenceMapper;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StudyTagPersistenceAdapter implements StudyTagCommandPort {

	private final JpaStudyTagRepository studyTagRepository;
	private final StudyTagPersistenceMapper studyTagPersistenceMapper;

	@Override
	public List<String> saveAllStudyTags(List<String> studyTags, Long studyId) {
		List<StudyTagJpaEntity> entities =
				studyTagRepository.saveAll(studyTagPersistenceMapper.toEntities(studyTags, studyId));

		return studyTagPersistenceMapper.toDomains(entities);
	}

	@Override
	public void clearStudyTags(Long studyId) {
		studyTagRepository.deleteAllByStudyId(studyId);
	}

	@Override
	public void replaceStudyTags(List<String> newStudyTags, Long studyId) {
		clearStudyTags(studyId);
		saveAllStudyTags(newStudyTags, studyId);
	}
}
