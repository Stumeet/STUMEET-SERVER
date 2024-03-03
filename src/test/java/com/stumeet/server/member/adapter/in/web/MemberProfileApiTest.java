package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.member.adapter.out.persistence.JpaMemberRepository;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class MemberProfileApiTest extends ApiTest {

    @Autowired
    private JpaMemberRepository memberRepository;

    @Nested
    @DisplayName("내 프로필 수정")
    class UpdateMyProfile {

        private final String path = "/api/v1/members/me";

        @BeforeEach
        void setUp() {
            memberRepository.save(MemberStub.getMemberEntity());
        }

        @Test
        @WithMockMember
        @DisplayName("[성공] 회원 프로필 수정에 성공한다.")
        void successTest() throws Exception {
            MemberUpdateCommand request = MemberStub.getMemberUpdateCommand();

            RequestPostProcessor patchMethod = http -> {
                http.setMethod("PATCH");
                return http;
            };
            mockMvc.perform(multipart(path)
                            .file((MockMultipartFile) request.image())
                            .with(patchMethod)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", String.valueOf(request.profession())))
                    .andExpect(status().isOk())
                    .andDo(document("update-my-profile/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            requestParts(partWithName("image").description("프로필 이미지 파일 (Optional)")),
                            queryParameters(
                                    parameterWithName("nickname").description("닉네임 (Optional)"),
                                    parameterWithName("region").description("지역 (Optional)"),
                                    parameterWithName("profession").description("직업 ID (Optional)")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 요청이 유효하지 않은 경우 프로필 수정에 실패한다.")
        void invalidRequestTest() throws Exception {
            MemberUpdateCommand request = MemberStub.getInvalidMemberUpdateCommand();

            RequestPostProcessor patchMethod = http -> {
                http.setMethod("PATCH");
                return http;
            };
            mockMvc.perform(multipart(path)
                            .file((MockMultipartFile) request.image())
                            .with(patchMethod)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", String.valueOf(request.profession())))
                    .andExpect(status().isBadRequest())
                    .andDo(document("update-my-profile/fail/invalid-request",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            requestParts(partWithName("image").description("프로필 이미지 파일 (Optional)")),
                            queryParameters(
                                    parameterWithName("nickname").description("닉네임 (Optional)"),
                                    parameterWithName("region").description("지역 (Optional)"),
                                    parameterWithName("profession").description("직업 ID (Optional)")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data[].message").description("유효하지 않은 요청에 대한 상세 메시지")
                            )));
        }
    }
}