package com.stumeet.server.studymember.domain;

import com.stumeet.server.studymember.domain.exception.AlreadySentGrapeException;
import com.stumeet.server.studymember.domain.exception.SelfGrapePraiseForbiddenException;

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

    public void resetGrapeStatus() {
        this.isSentGrape = false;
    }

    public void markGrapeSent() {
        this.isSentGrape = true;
    }

    public void checkAlreadySentGrape() {
        if (this.isSentGrape()) {
            throw new AlreadySentGrapeException();
        }
    }

    public void checkSelfPraise(long receiverId) {
        if (this.id == receiverId) {
            throw new SelfGrapePraiseForbiddenException();
        }
    }
}
