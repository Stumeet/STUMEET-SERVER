package com.stumeet.server.studymember.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "profession")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class JoinMemberProfessionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스터디 참여 멤버의 분야 ID")
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    @Comment("이름")
    private String name;

}
