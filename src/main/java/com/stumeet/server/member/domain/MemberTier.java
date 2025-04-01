package com.stumeet.server.member.domain;

import java.util.Arrays;
import java.util.Comparator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberTier {
    SEED("씨앗", 20),
    SPROUT("새싹", 100),
    LEAF("잎", 500),
    FLOWER("꽃", 1000),
    TREE("나무", 2000),
    FRUIT("열매", Integer.MAX_VALUE);

    private final String name;
    private final double threshold;

    public static MemberTier getTierByXP(double xp) {
        return Arrays.stream(values())
                .sorted(Comparator.comparingDouble(MemberTier::getThreshold))
                .filter(tier -> xp <= tier.threshold)
                .findFirst()
                .orElse(FRUIT);
    }

    public boolean canLevelUp(double xp) {
        return xp > this.threshold;
    }
}
