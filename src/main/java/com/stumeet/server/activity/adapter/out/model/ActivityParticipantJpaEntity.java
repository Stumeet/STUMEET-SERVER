package com.stumeet.server.activity.adapter.out.model;

import com.stumeet.server.activity.adapter.out.mapper.ActivityStatusConverter;
import com.stumeet.server.activity.domain.model.ActivityStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "activity_participant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityParticipantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("활동 참여자 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @Comment("참여하는 활동")
    private ActivityJpaEntity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("참여하는 멤버")
    private ActivityMemberJpaEntity member;

    @Convert(converter = ActivityStatusConverter.class)
    @Comment("활동 참여자 상태")
    private ActivityStatus status;
}
