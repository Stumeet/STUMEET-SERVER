package com.stumeet.server.member.application.port.in.command;

import com.stumeet.server.profession.domain.Profession;
import lombok.Builder;


@Builder
public record MemberProfileCommand(
        Profession profession,
        String url,
        String nickname,
        String region
) {
}
