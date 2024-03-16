package com.stumeet.server.studymember.adapter.out.persistence;

import com.stumeet.server.common.model.BaseTimeEntity;
import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.study.adapter.out.persistance.entity.StudyJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "study_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudyMemberJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스터디 멤버 ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @Comment("멤버")
    private MemberJpaEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Comment("스터디")
    private StudyJpaEntity study;

    @Column(name = "is_admin")
    @Comment("스터디 관리자 여부")
    private boolean isAdmin;

    @Column(name = "is_sent_grape")
    @Comment("포도알 전송 여부")
    private boolean isSentGrape;
}
