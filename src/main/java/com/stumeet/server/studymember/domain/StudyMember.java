package com.stumeet.server.studymember.domain;

import com.stumeet.server.member.domain.Member;
import com.stumeet.server.study.domain.Study;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudyMember {
    private Long id;

    private Member member;

    private Study study;

    private boolean isAdmin;

    private boolean isSentGrape;
}
