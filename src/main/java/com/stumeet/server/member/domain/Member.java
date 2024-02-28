package com.stumeet.server.member.domain;

import com.stumeet.server.file.application.port.out.FileUrl;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import com.stumeet.server.profession.domain.Profession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member {
    private Long id;

    private Profession profession;

    private String name;

    private String image;

    private Double sugarContents;

    private String region;

    private AuthType authType;

    private UserRole role;

    public void registerWithAdditionalDetails(MemberSignupCommand request, FileUrl profileImage, Profession profession) {
        this.image = profileImage.url();
        this.name = request.nickname();
        this.region = request.region();
        this.profession = profession;
        this.role = UserRole.MEMBER;
    }
}
