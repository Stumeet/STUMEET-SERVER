package com.stumeet.server.stub;

import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.member.application.port.in.MemberSignupCommand;
import com.stumeet.server.member.domain.AuthType;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.member.domain.UserRole;
import com.stumeet.server.helper.WithMockMember;
import org.springframework.mock.web.MockMultipartFile;

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
                .name("test")
                .role(UserRole.FIRST_LOGIN)
                .authType(AuthType.OAUTH)
                .sugarContents(0.0)
                .build();
    }

    public static MemberSignupCommand getMemberSignupCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        return new MemberSignupCommand(image, "test", "서울", 1L);
    }

    public static Member getMember(WithMockMember annotation) {
        return Member.builder()
                .id(1L)
                .name("test")
                .role(annotation.authority())
                .authType(AuthType.OAUTH)
                .sugarContents(0.0)
                .profession(null)
                .region(null)
                .image(null)
                .build();
    }
}
