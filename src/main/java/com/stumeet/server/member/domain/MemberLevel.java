package com.stumeet.server.member.domain;

import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberLevel {

    private MemberTier tier;
    private double experience;
}
