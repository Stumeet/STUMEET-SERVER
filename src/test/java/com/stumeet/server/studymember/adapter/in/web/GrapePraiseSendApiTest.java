package com.stumeet.server.studymember.adapter.in.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.GrapeStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.studymember.adapter.in.web.dto.GrapeSendRequest;
import com.stumeet.server.template.ApiTest;

class GrapePraiseSendApiTest extends ApiTest {

    @Nested
    @DisplayName("포도알 칭찬 전송 API")
    class SendGrapePraise {

        private final String path = "/api/v1/reviews/grape";

        @Test
        @WithMockMember
        @DisplayName("[성공] 포도알 칭찬 전송에 성공한다.")
        void success() throws Exception {
            GrapeSendRequest request = GrapeStub.getGrapeSendRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isCreated())
                .andDo(document("send-grape-praise/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("studyMemberId").description("칭찬 받을 멤버의 스터디 멤버 ID"),
                        fieldWithPath("complimentType").description("칭찬 타입")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 유효하지 않은 칭찬 타입으로 요청한 경우 포도알 칭찬 전송에 실패한다.")
        void fail_when_invalid_compliment_type() throws Exception {
            GrapeSendRequest request = GrapeStub.getInvalidComplimentTypeGrapeSendRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(document("send-grape-praise/fail/invalid-compliment-type",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("studyMemberId").description("칭찬 받을 멤버의 스터디 멤버 ID"),
                        fieldWithPath("complimentType").description("칭찬 타입")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 존재하지 않는 스터디 멤버 ID로 요청한 경우 포도알 칭찬 전송에 실패한다.")
        void fail_when_study_member_not_found() throws Exception {
            GrapeSendRequest request = GrapeStub.getNotExistStudyMemberGrapeSendRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isNotFound())
                .andDo(document("send-grape-praise/fail/study-member-not-found",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("studyMemberId").description("칭찬 받을 멤버의 스터디 멤버 ID"),
                        fieldWithPath("complimentType").description("칭찬 타입")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 전송자가 해당 스터디의 스터디원이 아닐 때 포도알 칭찬 전송에 실패한다.")
        void fail_when_sender_is_not_the_study_member() throws Exception {
            GrapeSendRequest request = GrapeStub.getGrapeSendRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isNotFound())
                .andDo(document("send-grape-praise/fail/sender_is_not_the_study_member",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("studyMemberId").description("칭찬 받을 멤버의 스터디 멤버 ID"),
                        fieldWithPath("complimentType").description("칭찬 타입")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[실패] 전송자가 해당 스터디의 이미 이번주 포도알을 전송한 경우 포도알 칭찬 전송에 실패한다.")
        void fail_when_sender_already_sent_grape() throws Exception {
            GrapeSendRequest request = GrapeStub.getGrapeSendRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isConflict())
                .andDo(document("send-grape-praise/fail/sender_already_sent_grape",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("studyMemberId").description("칭찬 받을 멤버의 스터디 멤버 ID"),
                        fieldWithPath("complimentType").description("칭찬 타입")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }
    }
}