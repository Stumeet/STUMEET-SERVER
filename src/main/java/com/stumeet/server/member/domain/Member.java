package com.stumeet.server.member.domain;

import com.stumeet.server.file.application.port.out.FileUrl;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member {
    private Long id;

    private String name;

    private String image;

    private Double sugarContents;

    private String region;

    private String profession;

    private AuthType authType;

    private UserRole role;

    public void registerWithAdditionalDetails(MemberSignupCommand request, FileUrl profileImage) {
        this.image = profileImage.url();
        this.name = request.nickname();
        this.region = request.region();
        this.profession = request.profession();
        this.role = UserRole.MEMBER;
    }
}
