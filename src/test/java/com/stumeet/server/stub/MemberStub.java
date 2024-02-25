package com.stumeet.server.stub;

public class MemberStub {

    private MemberStub() {

    }

    public static String getKakaoAccessTokenInfo() {
        return "{\"expiresInMillis\":20978166,\"id\":1,\"expires_in\":20978,\"app_id\":1,\"appId\":1}";
    }
}
