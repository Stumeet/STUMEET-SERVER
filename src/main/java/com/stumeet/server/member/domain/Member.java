package com.stumeet.server.member.domain;

import com.stumeet.server.member.application.port.in.command.MemberProfileCommand;
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

    private MemberLevel level;

    private String region;

    private AuthType authType;

    private UserRole role;

    public void signup(MemberProfileCommand command) {
        this.image = command.url();
        this.name = command.nickname();
        this.region = command.region();
        this.profession = command.profession();
        this.role = UserRole.MEMBER;
    }

    public void updateProfile(MemberProfileCommand command) {
        this.image = command.url() == null ? this.image : command.url();
        this.name = command.nickname() == null ? this.name : command.nickname();
        this.region = command.region() == null ? this.region : command.region();
        this.profession = command.profession() == null ? this.profession : command.profession();
    }
}
