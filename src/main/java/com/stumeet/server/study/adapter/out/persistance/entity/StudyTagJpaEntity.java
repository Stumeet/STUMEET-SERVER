package com.stumeet.server.study.adapter.out.persistance.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "study_tag")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyTagJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("스터디 태그 id")
	private Long id;

	@Column(name = "study_id", nullable = false)
	@Comment("스터디 id")
	private Long studyId;

	@Column(name = "name", length = 10, nullable = false)
	@Comment("태그명")
	private String name;
}
