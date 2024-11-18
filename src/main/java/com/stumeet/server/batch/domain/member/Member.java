package com.stumeet.server.batch.domain.member;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.member.domain.AuthType;
import com.stumeet.server.member.domain.MemberTier;
import com.stumeet.server.member.domain.UserRole;
import com.stumeet.server.profession.adapter.out.persistence.ProfessionJpaEntity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private ProfessionJpaEntity profession;

    @OneToMany(mappedBy = "member")
    private List<Device> devices;

    private String name;

    private String image;

    @Enumerated(EnumType.STRING)
    private MemberTier tier;

    private double experience;

    private String region;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private AuthType authType;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
