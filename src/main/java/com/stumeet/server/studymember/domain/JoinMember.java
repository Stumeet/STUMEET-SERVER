package com.stumeet.server.studymember.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class JoinMember {
    private Long id;

    private String name;

    private String image;

    private JoinMemberProfession profession;

    private String region;
}
