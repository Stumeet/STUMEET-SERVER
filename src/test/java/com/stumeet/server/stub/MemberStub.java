package com.stumeet.server.stub;

import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.member.adapter.in.web.response.MemberProfileResponse;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.domain.*;
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
                .profession(ProfessionStub.getProfession())
                .region("서울")
                .image(FileStub.getFileUrl().url())
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
                .profession(ProfessionStub.getProfession())
                .region("서울")
                .image(FileStub.getFileUrl().url())
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

    public static MemberProfileResponse getMemberProfileResponse(Member member) {
        return MemberProfileResponse.builder()
                .id(member.getId())
                .image(member.getImage())
                .nickname(member.getName())
                .region(member.getRegion())
                .profession(member.getProfession().getName())
                .rank(member.getLevel().getRank().getName())
                .experience(member.getLevel().getExperience())
                .build();
    }

    public static String getAppleInvalidSignatureTokenInfo() {
        return "{\"keys\":[{\"kty\":\"RSA\",\"kid\":\"lVHdOx8ltR\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"nXDu9MPf6dmVtFbDdAaal_0cO9ur2tqrrmCZaAe8TUWHU8AprhJG4DaQoCIa4UsOSCbCYOjPpPGGdE_p0XeP1ew55pBIquNhNtNNEMX0jNYAKcA9WAP1zGSkvH5m39GMFc4SsGiQ_8Szht9cayJX1SJALEgSyDOFLs-ekHnexqsr-KPtlYciwer5jaNcW3B7f9VNp1XCypQloQwSGVismPHwDJowPQ1xOWmhBLCK50NV38ZjobUDSBbCeLYecMtsdL5ZGv-iufddBh3RHszQiD2G-VXoGOs1yE33K4uAto2F2bHVcKOUy0__9qEsXZGf-B5ZOFucUkoN7T2iqu2E2Q\",\"e\":\"AQAB\"},{\"kty\":\"RSA\",\"kid\":\"pyaRQpAbnY\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"qHiwOpizi6xHG8FIOSWH4l0P1CjLIC7aBFkhbk7BrD4s9KQAs5Sj5xAtOwlZMyP2XFcqRtZBLIMM7vw_CNERtRrhc68se5hQE_vsrHy7ugcQU6ogJS6s54zqO-zTUfaa3mABM6iR-EfgSpvz33WTQZAPtwAyxaSLknHyDzWjHEZ44WqaQBdcMAvgsWMYG5dBfnV-3Or3V2r1vdbinRE5NomE2nsKDbnJ3yo3u-x9TizKazS1JV3umt71xDqbruZLybIrimrzg_i9OSIzT2o5ZWz8zdYkKHZ4cvRPh-DDt8kV7chzR2tenPF2c5WXuK-FumOrjT7WW6uwSvhnhwNZuw\",\"e\":\"AQAB\"},{\"kty\":\"RSA\",\"kid\":\"fh6Bs8C\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"u704gotMSZc6CSSVNCZ1d0S9dZKwO2BVzfdTKYz8wSNm7R_KIufOQf3ru7Pph1FjW6gQ8zgvhnv4IebkGWsZJlodduTC7c0sRb5PZpEyM6PtO8FPHowaracJJsK1f6_rSLstLdWbSDXeSq7vBvDu3Q31RaoV_0YlEzQwPsbCvD45oVy5Vo5oBePUm4cqi6T3cZ-10gr9QJCVwvx7KiQsttp0kUkHM94PlxbG_HAWlEZjvAlxfEDc-_xZQwC6fVjfazs3j1b2DZWsGmBRdx1snO75nM7hpyRRQB4jVejW9TuZDtPtsNadXTr9I5NjxPdIYMORj9XKEh44Z73yfv0gtw\",\"e\":\"AQAB\"},{\"kty\":\"RSA\",\"kid\":\"Bh6H7rHVmb\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"2HkIZ7xKMUYH_wtt2Gwq6jXKRl-Ng5vdwd-XcWn5RIW82-uxdmGJyTo3T6MPty-xWUeW7FCs9NlM4yu02GKgwep7TKfnOovP78sd3rESbZsvuN7zD_Vk6aZP7QfqblElUtiMQxh9bu-gZUeMZfa_ndX-P5C4yKtZvXGrSPLLjyAcSmSHNLZnWbZXjeIVsgXWHMr5fwVEAkftHq_4py82xgn2XEK0FK9HmWOCZ47Wcx9fWBnqSi9JTJTUX0lh-kI5TcYfv9UKX2oe3uyOn-A460E_L_4ximlM-lgi3otw26EZfAGY9FFgSZoACjhgw_z5NRbK9dycHRpeLY9GxIyiYw\",\"e\":\"AQAB\"}]}";
    }
}
