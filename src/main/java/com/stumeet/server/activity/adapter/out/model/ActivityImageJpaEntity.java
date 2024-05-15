package com.stumeet.server.activity.adapter.out.model;

import com.stumeet.server.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "activity_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityImageJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("활동 이미지 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @Comment("활동")
    private ActivityJpaEntity activity;

    @Column(name = "image", nullable = false, length = 500)
    @Comment("이미지 url")
    private String url;
}
