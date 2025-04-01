package com.stumeet.server.member.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.stumeet.server.template.UnitTest;

@DisplayName("멤버 레벨 시스템")
class MemberTierTest extends UnitTest {

    @Test
    @DisplayName("[성공] 경험치에 따라 올바른 티어가 반환되야 한다.")
    void shouldReturnCorrectTierByXP() {
        // given
        double xp1 = 20;
        double xp2 = 100;
        double xp3 = 700;
        double xp4 = 2500;

        // when
        MemberTier tier1 = MemberTier.getTierByXP(xp1);
        MemberTier tier2 = MemberTier.getTierByXP(xp2);
        MemberTier tier3 = MemberTier.getTierByXP(xp3);
        MemberTier tier4 = MemberTier.getTierByXP(xp4);

        // then
        assertThat(tier1).isEqualTo(MemberTier.SEED);
        assertThat(tier2).isEqualTo(MemberTier.SPROUT);
        assertThat(tier3).isEqualTo(MemberTier.FLOWER);
        assertThat(tier4).isEqualTo(MemberTier.FRUIT);
    }

    @Test
    @DisplayName("[성공] 경험치가 티어 임계값을 넘었을 때 레벨업 가능 여부를 확인 가능하다.")
    void shouldCheckIfCanLevelUp() {
        // given
        double xp1 = 20;
        double xp2 = 101;
        double xp3 = 1500;
        double xp4 = 2001;

        // when & then
        assertThat(MemberTier.SEED.canLevelUp(xp1)).isFalse();
        assertThat(MemberTier.SPROUT.canLevelUp(xp2)).isTrue();
        assertThat(MemberTier.TREE.canLevelUp(xp3)).isFalse();
        assertThat(MemberTier.TREE.canLevelUp(xp4)).isTrue();
    }
}