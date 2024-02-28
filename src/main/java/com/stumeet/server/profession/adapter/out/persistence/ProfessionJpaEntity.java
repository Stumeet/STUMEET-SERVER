package com.stumeet.server.profession.adapter.out.persistence;

import com.stumeet.server.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "profession")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ProfessionJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("분야 ID")
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment("이름")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Comment("대분류 분야")
    private ProfessionJpaEntity parent;
}
