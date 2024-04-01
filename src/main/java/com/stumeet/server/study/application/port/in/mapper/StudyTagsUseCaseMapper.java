package com.stumeet.server.study.application.port.in.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.stumeet.server.study.domain.StudyTag;

@Component
public class StudyTagsUseCaseMapper {

	public List<StudyTag> toDomains(List<String> tags) {
		return tags.stream()
				.map(this::toDomain)
				.toList();
	}

	private StudyTag toDomain(String tag) {
		return StudyTag.builder()
				.name(tag)
				.build();
	}
}
