package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.member.adapter.out.persistence.JpaMemberRepository;
import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import com.stumeet.server.helper.WithMockMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberValidApiTest extends ApiTest {

    @Autowired
    private JpaMemberRepository jpaMemberRepository;

    @Nested
    @DisplayName("닉네임 중복 검증")
    class IsDuplicateNickname {

        private final String path = "/api/v1/members/validate-nickname";

        @Test
        @WithMockMember
        @DisplayName("[성공] 유효한 닉네임을 입력하면 중복 검증에 통과한다.")
        void successTest() throws Exception {
            String nickname = "닉네임";
            mockMvc.perform(get(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .param("nickname", nickname))
                    .andExpect(status().isOk())
                    .andDo(document("validate_nickname/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("nickname").description("검증할 닉네임")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").description("응답에 대한 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 유효하지 않은 닉네임을 입력하면 검증에 실패합니다.")
        void invalidRequestTest() throws Exception {
            String nickname = "닉";
            mockMvc.perform(get(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .param("nickname", nickname))
                    .andExpect(status().isBadRequest())
                    .andDo(document("validate_nickname/fail/invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("nickname").description("검증할 닉네임")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").description("응답에 대한 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 닉네임이 중복되면 검증에 실패합니다.")
        void duplicateNicknameTest() throws Exception {
            String nickname = MemberStub.getMember().getName();
            mockMvc.perform(get(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .param("nickname", nickname))
                    .andExpect(status().isBadRequest())
                    .andDo(document("validate_nickname/fail/duplicate",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("nickname").description("검증할 닉네임")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").description("응답에 대한 메시지")
                            )));
        }
    }
}