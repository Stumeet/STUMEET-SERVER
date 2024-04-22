package com.stumeet.server.activity.adapter.out.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityMemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("활동 멤버 id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment("멤버 이름")
    private String name;

    @Column(name = "image", nullable = false)
    @Comment("멤버 이미지")
    private String image;
}
