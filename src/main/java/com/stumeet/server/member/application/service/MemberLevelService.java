package com.stumeet.server.member.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.event.EventPublisher;
import com.stumeet.server.member.adapter.out.event.model.MemberLevelUpAlertEvent;
import com.stumeet.server.member.application.port.in.MemberLevelUseCase;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.MemberLevel;
import com.stumeet.server.member.domain.MemberTier;

import lombok.RequiredArgsConstructor;

@Transactional
@UseCase
@RequiredArgsConstructor
public class MemberLevelService implements MemberLevelUseCase {
    private final MemberQueryPort memberQueryPort;
    private final MemberCommandPort memberCommandPort;

    @Override
    public void progress(long memberId, int xp) {
        Member member = memberQueryPort.getById(memberId);
        MemberLevel level = member.getLevel();
        MemberTier tier = level.getTier();

        level.gainXP(xp);

        if (tier.canLevelUp(level.getExperience())) {
            level.levelUp();
            EventPublisher.raise(new MemberLevelUpAlertEvent(this, member.getId(), tier.getName()));
        }

        memberCommandPort.update(member);
    }
}
