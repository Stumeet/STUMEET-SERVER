package com.stumeet.server.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRank {
    SEED("씨앗"),
    SPROUT("새싹"),
    LEAF("잎"),
    FLOWER("꽃"),
    TREE("나무"),
    FRUIT("열매");


    private final String name;

}
