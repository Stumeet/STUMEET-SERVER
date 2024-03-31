package com.stumeet.server.study.adapter.out.persistance.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "study_domain_id")
	@Comment("스터디 관련")
	private StudyDomainJpaEntity studyDomain;

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
	private LocalDateTime startDate;

	@Column(name = "end_date", nullable = false)
	@Comment("종료일")
	private LocalDateTime endDate;

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
