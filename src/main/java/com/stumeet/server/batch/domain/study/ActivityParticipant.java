package com.stumeet.server.batch.domain.study;

import com.stumeet.server.activity.adapter.out.mapper.ActivityStatusConverter;
import com.stumeet.server.activity.domain.model.ActivityStatus;
import com.stumeet.server.batch.domain.member.Member;
import com.stumeet.server.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "activity_participant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityParticipant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = ActivityStatusConverter.class)
    private ActivityStatus status;
}

