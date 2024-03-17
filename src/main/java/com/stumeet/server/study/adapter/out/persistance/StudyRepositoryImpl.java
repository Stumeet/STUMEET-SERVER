package com.stumeet.server.study.adapter.out.persistance;

import org.springframework.stereotype.Repository;

import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepository {

	private final JpaStudyRepository jpaStudyRepository;

	@Override
	public StudyJpaEntity findById(Long id) {
		return jpaStudyRepository.findById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.STUDY_NOT_FOUND));
	}

	@Override
	public boolean existsById(Long id) {
		return jpaStudyRepository.existsById(id);
	}
}
