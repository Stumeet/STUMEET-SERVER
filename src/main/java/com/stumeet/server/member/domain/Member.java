package com.stumeet.server.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
