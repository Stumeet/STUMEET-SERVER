package com.stumeet.server.study.adapter.out.persistance.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "study")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyJpaEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("스터디 그룹 아이디")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "study_field_id", nullable = false)
	@Comment("분야")
	private StudyFieldJpaEntity studyField;

	@Column(name = "name", length = 20, nullable = false)
	@Comment("스터디명")
	private String name;

	@Column(name = "topics")
	@Comment("주제")
	private String topics;

	@Column(name = "intro", length = 100, nullable = false)
	@Comment("소개")
	private String intro;

	@Column(name = "region", length = 50, nullable = false)
	@Comment("지역")
	private String region;

	@Column(name = "rule", length = 100)
	@Comment("규칙")
	private String rule;

	@Column(name = "main_image", length = 500)
	@Comment("메인 이미지")
	private String mainImage;

	@Column(name = "head_count", length = 100, nullable = false)
	@ColumnDefault("1")
	@Comment("현재 인원")
	private int headCount;

	@Column(name = "start_date", nullable = false)
	@Comment("시작일")
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	@Comment("종료일")
	private LocalDateTime endDate;

	@Column(name = "is_finished", nullable = false)
	@ColumnDefault("false")
	@Comment("완료 여부")
	private boolean isFinished;

	@Column(name = "is_deleted", nullable = false)
	@ColumnDefault("false")
	@Comment("삭제 여부")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	@Comment("삭제된 시간")
	private LocalDateTime deletedAt;

	public Boolean getIsFinished() {
		return this.isFinished;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
}
