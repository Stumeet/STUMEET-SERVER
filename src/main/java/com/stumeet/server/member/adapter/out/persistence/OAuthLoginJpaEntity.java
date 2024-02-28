package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.member.domain.OAuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "oauth_login")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class OAuthLoginJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("OAuth 로그인 정보 ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @Comment("멤버 ID")
    private MemberJpaEntity member;

    @Column(name = "provider_name", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("제공자 이름(kakao, apple)")
    private OAuthProvider providerName;

    @Column(name = "provider_id", length = 50, nullable = false)
    @Comment("OAuth 사용자 고유 아이디")
    private String providerId;
}
