package com.stumeet.server.review.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.stumeet.server.common.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "review")
public class ReviewJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    List<ReviewTagJpaEntity> tags;

    @Column(name = "reviewer_id", nullable = false)
    private Long reviewerId;

    @Column(name = "reviewee_Id", nullable = false)
    private Long revieweeId;

    @Column(name = "study_id", nullable = false)
    private Long studyId;

    @Column(nullable = false)
    private Integer rate;

    @Column(length = 1000)
    private String content;
}
