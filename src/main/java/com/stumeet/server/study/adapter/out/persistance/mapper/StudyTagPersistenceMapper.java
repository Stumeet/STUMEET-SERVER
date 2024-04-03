package com.stumeet.server.study.adapter.out.persistance.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;
import com.stumeet.server.study.domain.StudyTag;

@Component
public class StudyTagPersistenceMapper {

	public List<StudyTag> toDomains(List<StudyTagJpaEntity> entities) {
		return entities != null
				? entities.stream()
				.map(tagEntity -> StudyTag.builder()
						.id(tagEntity.getId())
						.name(tagEntity.getName())
						.build())
				.toList()
				: null;
	}

	public List<StudyTagJpaEntity> toEntities(List<StudyTag> domains, Long studyDomainId) {
		return domains != null
				? domains.stream()
				.map(tagDomain -> StudyTagJpaEntity.builder()
						.id(tagDomain.getId())
						.studyDomainId(studyDomainId)
						.name(tagDomain.getName())
						.build())
				.toList()
				: null;
	}
}
