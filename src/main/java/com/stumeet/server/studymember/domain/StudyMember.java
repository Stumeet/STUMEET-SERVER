package com.stumeet.server.studymember.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudyMember {
    private Long id;

    private JoinMember member;

    private JoinStudy study;

    private boolean isAdmin;

    private boolean isSentGrape;

    private boolean isLegacyHidden;

    public void hideLegacyStudy() {
        this.isLegacyHidden = true;
    }
}
