package com.stumeet.server.notification.adapter.out.persistence.entity;

import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.notification.domain.TopicReferType;

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
@Table(name = "topic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class TopicJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("토픽 ID")
    private Long id;

    @Comment("토픽 이름")
    private String name;

    @Comment("토픽 설명")
    private String description;

    @Comment("참조 유형")
    private TopicReferType referType;

    @Comment("참조 ID")
    private Long referId;
}
