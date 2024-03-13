package com.stumeet.server.study.adapter.out.persistance.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	@OneToOne(cascade = CascadeType.ALL)
	@Column(name = "study_id")
	@Comment("스터디 그룹 아이디")
	private StudyJpaEntity studyId;

	@Column(name = "tag1", length = 10, nullable = true)
	private String tag1;

	@Column(name = "tag2", length = 10, nullable = true)
	private String tag2;

	@Column(name = "tag3", length = 10, nullable = true)
	private String tag3;

	@Column(name = "tag4", length = 10, nullable = true)
	private String tag4;

	@Column(name = "tag5", length = 10, nullable = true)
	private String tag5;
}
