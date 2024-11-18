package com.stumeet.server.report.adapter.in;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.report.adapter.in.request.ReportCreateRequest;
import com.stumeet.server.report.application.port.in.ReportAlertUseCase;
import com.stumeet.server.report.application.port.out.ReportAlertPort;
import com.stumeet.server.stub.ReportStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportCreateApiTest extends ApiTest {

    @MockBean
    private ReportAlertPort reportAlertPort;

    @Nested
    @DisplayName("스터디/활동 신고")
    class Report {
        private static final String PATH = "/api/v1/report";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 신고에 성공한다.")
        void success_study_report() throws Exception {
            ReportCreateRequest request = ReportStub.getStudyReportCreateRequest();

            mockMvc.perform(post(PATH)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isCreated())
                .andDo(document("create-report/study/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("category").description("신고 카테고리 `STUDY`/`ACTIVITY`"),
                        fieldWithPath("reason").description("신고 사유"),
                        fieldWithPath("reportedId").description("신고된 ID (스터디 혹은 활동의 ID)"),
                        fieldWithPath("reporterId").description("신고자의 member ID"),
                        fieldWithPath("content").description("신고 내용")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지")
                    )
                ));
        }

        @Test
        @WithMockMember
        @DisplayName("[성공] 활동 신고에 성공한다.")
        void success_activity_report() throws Exception {
            ReportCreateRequest request = ReportStub.getActivityReportCreateRequest();

            mockMvc.perform(post(PATH)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isCreated())
                .andDo(document("create-report/activity/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("category").description("신고 카테고리"),
                        fieldWithPath("reason").description("신고 사유"),
                        fieldWithPath("reportedId").description("신고된 ID (스터디 혹은 활동의 ID)"),
                        fieldWithPath("reporterId").description("신고자의 member ID"),
                        fieldWithPath("content").description("신고 내용")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지")
                    )
                ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 신고자를 찾을 수 없는 경우 신고에 실패한다.")
        void fail_when_reporter_not_found() throws Exception {
            ReportCreateRequest request = ReportStub.getNotFoundReporterIdReportCreateRequest();

            mockMvc.perform(post(PATH)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isNotFound())
                .andDo(document("create-report/fail/reporter-not-found",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("category").description("신고 카테고리 `STUDY`/`ACTIVITY`"),
                        fieldWithPath("reason").description("신고 사유"),
                        fieldWithPath("reportedId").description("신고된 ID (스터디 혹은 활동의 ID)"),
                        fieldWithPath("reporterId").description("신고자의 member ID"),
                        fieldWithPath("content").description("신고 내용")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지")
                    )
                ));
        }
    }
}