package com.stumeet.server.notification.adapter.out.persistence.entity;

import java.util.Map;

import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.notification.adapter.out.mapper.JsonToMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "notification_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NotificationLogJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("알림 기록 ID")
    private Long id;

    @Column(name = "member_id")
    @Comment("멤버 ID")
    private Long memberId;

    @Column(name = "title")
    @Comment("제목")
    private String title;

    @Column(name = "body")
    @Comment("본문")
    private String body;

    @Column(name = "img_url")
    @Comment("커스텀 뱃지 이미지 URL")
    private String imgUrl;

    @Column(name = "data")
    @Comment("메타 데이터")
    @Convert(converter = JsonToMapConverter.class)
    private Map<String, String> data;
}