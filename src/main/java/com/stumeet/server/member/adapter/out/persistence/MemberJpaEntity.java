package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.member.domain.AuthType;
import com.stumeet.server.member.domain.MemberTier;
import com.stumeet.server.member.domain.UserRole;
import com.stumeet.server.profession.adapter.out.persistence.ProfessionJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MemberJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("멤버 아이디")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    @Comment("분야")
    private ProfessionJpaEntity profession;

    @Column(name = "name")
    @Comment("멤버 이름")
    private String name;

    @Column(name = "image")
    @Comment("멤버 이미지 URL")
    private String image;

    @Column(name = "tier", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("등급")
    private MemberTier tier;

    @Column(name = "experience", nullable = false)
    @Comment("경험치")
    private double experience;

    @Column(name = "region", length = 50)
    @Comment("지역")
    private String region;

    @Column(name = "auth_type", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("인증 방법(OAuth, 자체 로그인 등)")
    private AuthType authType;

    @Column(name = "role", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("권한(FIRST_LOGIN, MEMBER)")
    private UserRole role;

    @Column(name = "is_deleted", nullable = false)
    @Comment("삭제 여부")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    @Comment("삭제된 시간")
    private LocalDateTime deletedAt;

}
