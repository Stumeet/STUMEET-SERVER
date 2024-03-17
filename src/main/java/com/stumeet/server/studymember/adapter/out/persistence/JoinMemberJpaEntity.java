package com.stumeet.server.studymember.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class JoinMemberJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스터디 참여 중인 멤버 ID")
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    @Comment("스터디 참여 멤버 이름")
    private String name;

    @Column(name = "image")
    @Comment("스터디 참여 멤버 프로필 사진")
    private String image;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    @Comment("스터디 참여 멤버 분야")
    private JoinMemberProfessionJpaEntity profession;

    @Column(name = "region", length = 50, nullable = false)
    @Comment("스터디 참여 멤버 지역")
    private String region;
}
