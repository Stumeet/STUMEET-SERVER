package com.stumeet.server.study.adapter.out.persistance.entity;

import com.stumeet.server.study.domain.StudyField;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
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

	@OneToMany(mappedBy = "studyId", fetch = FetchType.EAGER)
	private List<StudyTagJpaEntity> studyTags;

	@Enumerated(EnumType.STRING)
	@Column(name = "studyField", length = 20, nullable = false)
	@Comment("분야명")
	private StudyField studyField;

	@Column(name = "name", length = 20, nullable = false)
	@Comment("스터디명")
	private String name;

	@Column(name = "intro", length = 100, nullable = false)
	@Comment("소개")
	private String intro;

	@Column(name = "region", length = 50, nullable = false)
	@Comment("지역")
	private String region;

	@Column(name = "rule", length = 100)
	@Comment("규칙")
	private String rule;

	@Column(name = "image", length = 500)
	@Comment("스터디 이미지")
	private String image;

	@Column(name = "headcount", length = 100, nullable = false)
	@ColumnDefault("1")
	@Comment("현재 인원")
	private int headcount;

	@Column(name = "start_date", nullable = false)
	@Comment("시작일")
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	@Comment("종료일")
	private LocalDate endDate;

	@Column(name = "meeting_time", nullable = false)
	@Comment("정기모임 시간")
	private LocalTime meetingTime;

	@Column(name = "meeting_repetition")
	@Comment("정기모임 반복")
	private String meetingRepetition;

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