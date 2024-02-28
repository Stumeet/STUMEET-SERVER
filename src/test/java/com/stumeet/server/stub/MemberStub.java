package com.stumeet.server.stub;

import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.member.domain.AuthType;
import com.stumeet.server.member.domain.UserRole;

public class MemberStub {

    private MemberStub() {

    }

    public static String getKakaoAccessTokenInfo() {
        return "{\"expiresInMillis\":20978166,\"id\":1,\"expires_in\":20978,\"app_id\":1,\"appId\":1}";
    }



    public static String getInvalidKakaoAccessTokenInfo() {
        return "{\"msg\":\"this access token does not exist\",\"code\":-401}";
    }

    public static MemberJpaEntity getMemberEntity() {
        return MemberJpaEntity.builder()
                .id(1L)
                .role(UserRole.FIRST_LOGIN)
                .authType(AuthType.OAUTH)
                .sugarContents(0.0)
                .build();
    }
}
