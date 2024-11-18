package com.stumeet.server.batch.domain.study;

import com.stumeet.server.study.adapter.out.persistance.entity.StudyTagJpaEntity;
import com.stumeet.server.study.domain.StudyField;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import org.hibernate.annotations.SQLDelete;

import com.stumeet.server.common.model.BaseTimeEntity;

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
@SQLDelete(sql = "UPDATE study SET is_deleted = true WHERE id = ? AND is_deleted = false")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Study extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "studyId")
    private List<StudyTagJpaEntity> studyTags;

    @Enumerated(EnumType.STRING)
    private StudyField studyField;

    private String name;

    private String intro;

    private String region;

    private String rule;

    private String image;

    private int headcount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "meeting_time")
    private LocalTime meetingTime;

    @Column(name = "meeting_repetition")
    private String meetingRepetition;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}