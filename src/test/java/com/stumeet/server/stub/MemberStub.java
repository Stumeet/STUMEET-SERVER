package com.stumeet.server.stub;

import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.domain.*;
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
                .rank(MemberRank.SEED)
                .experience(0.0)
                .build();
    }

    public static MemberSignupCommand getMemberSignupCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        return new MemberSignupCommand(image, "test", "서울", 1L);
    }

    public static Member getMember(WithMockMember annotation) {
        MemberLevel level = MemberLevel.builder()
                .rank(MemberRank.SEED)
                .experience(0.0)
                .build();

        return Member.builder()
                .id(1L)
                .name("test")
                .role(annotation.authority())
                .authType(AuthType.OAUTH)
                .level(level)
                .profession(null)
                .region(null)
                .image(null)
                .build();
    }

    public static Member getMember() {
        MemberLevel level = MemberLevel.builder()
                .rank(MemberRank.SEED)
                .experience(0.0)
                .build();
        return Member.builder()
                .id(1L)
                .name("test")
                .role(UserRole.MEMBER)
                .authType(AuthType.OAUTH)
                .level(level)
                .profession(null)
                .region(null)
                .image(null)
                .build();
    }

    public static MemberUpdateCommand getMemberUpdateCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        return new MemberUpdateCommand(image, "test2", "서울", 1L);
    }

    public static MemberUpdateCommand getInvalidMemberUpdateCommand() {
        MockMultipartFile invalidImage = new MockMultipartFile("image", "test.jpa", "plain/text", "test".getBytes());
        return new MemberUpdateCommand(invalidImage, "닉", "   ", -1L);
    }
}
