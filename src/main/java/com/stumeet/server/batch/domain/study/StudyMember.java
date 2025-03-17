package com.stumeet.server.batch.domain.study;

import org.hibernate.annotations.Comment;

import com.stumeet.server.common.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "study_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudyMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스터디 멤버 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Comment("스터디")
    private Study study;

    @Comment("멤버")
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "is_admin")
    @Comment("스터디 관리자 여부")
    private boolean isAdmin;

    @Column(name = "is_sent_grape")
    @Comment("포도알 전송 여부")
    private boolean isSentGrape;

    @Column(name = "is_legacy_hidden")
    @Comment("레거시 스터디 숨김 처리 여부")
    private boolean isLegacyHidden;

    public void resetGrape() {
        this.isSentGrape = false;
    }
}
