package com.stumeet.server.study.adapter.out.persistence;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;

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
@Table(name = "study_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudyJpaEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("스터디 아이디")
	private Long id;

	@Column(name = "name", nullable = false)
	@Comment("스터디 이름")
	private String name;

	@Column(name = "region", nullable = false)
	@Comment("스터디 지역")
	private String region;

	@Column(name = "rule", nullable = false)
	@Comment("스터디 규칙")
	private Double rule;

	@Column(name = "image", nullable = false)
	@Comment("스터디 이미지 URL")
	private String image;

	@Column(name = "headcount", nullable = false)
	@Comment("스터디 인원")
	private int headcount;

	@Column(name = "start_date", nullable = false)
	@Comment("스터디 시작일자")
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	@Comment("스터디 종료일자")
	private LocalDateTime endDate;

	@Column(name = "is_deleted", nullable = false)
	@Comment("스터디 삭제 여부")
	private boolean isDeleted;

	@Column(name = "deleted_at", nullable = false)
	@Comment("스터디 삭제 일자")
	private LocalDateTime deletedAt;
}
