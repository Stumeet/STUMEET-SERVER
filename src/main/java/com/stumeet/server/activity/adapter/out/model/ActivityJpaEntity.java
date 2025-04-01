package com.stumeet.server.activity.adapter.out.model;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("활동 id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    @Comment("연관된 스터디")
    private ActivityLinkedStudyJpaEntity study;

    @OneToOne
    @JoinColumn(name = "author_id")
    @Comment("활동을 생성한 멤버")
    private ActivityMemberJpaEntity author;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("활동 카테고리")
    private ActivityCategory category;

    @Column(name = "title", nullable = false, length = 100)
    @Comment("활동 제목")
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    @Comment("활동 내용")
    private String content;

    @Column(name = "is_notice", nullable = false)
    @Comment("공지 여부")
    private boolean isNotice;

    @Column(name = "start_date")
    @Comment("활동 시작일")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @Comment("활동 종료일")
    private LocalDateTime endDate;

    @Column(name = "location")
    @Comment("활동 장소")
    private String location;

    @Column(name = "link")
    @Comment("링크")
    private String link;
}
