package com.stumeet.server.report.adapter.out.model;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.report.domain.ReportCategory;
import com.stumeet.server.report.domain.ReportReason;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
@Table(name = "report")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class ReportJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private ReportCategory category;

    @Enumerated
    private ReportReason reason;

    private Long reportedId;

    private Long reporterId;

    private String content;
}

