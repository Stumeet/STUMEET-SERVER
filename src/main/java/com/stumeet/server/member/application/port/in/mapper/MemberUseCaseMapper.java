package com.stumeet.server.member.application.port.in.mapper;

import com.stumeet.server.member.adapter.in.web.response.MemberProfileResponse;
import com.stumeet.server.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberUseCaseMapper {

    public MemberProfileResponse toProfileResponse(Member member) {
        return MemberProfileResponse.builder()
                .id(member.getId())
                .image(member.getImage())
                .nickname(member.getName())
                .region(member.getRegion())
                .profession(member.getProfession().getName())
                .rank(member.getLevel().getRank().getName())
                .experience(member.getLevel().getExperience())
                .build();
    }
}
