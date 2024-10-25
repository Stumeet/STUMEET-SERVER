package com.stumeet.server.notification.adapter.out.persistence;

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
@Table(name = "notification_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class DeviceJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("등록 토큰 ID")
    private Long id;

    @Column(name = "member_id")
    @Comment("멤버 ID")
    private Long memberId;

    @Column(name = "device_id", unique = true, updatable = false)
    @Comment("기기 식별자")
    private String deviceId;

    @Column(name = "token")
    @Comment("알림 토큰")
    private String token;
}
