package com.stumeet.server.bff.adapter.in.app;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class StudyMemberHubApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 멤버 상세 화면 조회")
    class StudyMemberDetailFull {
        private static final String PATH = "/api/external/v1/studies/{studyId}/members/{memberId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 멤버 상세 화면 조회에 성공한다.")
        void success() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(get(PATH, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-member-detail-full/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("조회할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 멤버 상세 정보"),
                                    fieldWithPath("data.studyMemberDetailResponse.id").description("멤버 ID"),
                                    fieldWithPath("data.studyMemberDetailResponse.name").description("스터디 멤버 이름"),
                                    fieldWithPath("data.studyMemberDetailResponse.image").description("스터디 멤버 프로필 이미지"),
                                    fieldWithPath("data.studyMemberDetailResponse.region").description("스터디 멤버 지역"),
                                    fieldWithPath("data.studyMemberDetailResponse.profession").description("스터디 멤버 분야"),
                                    fieldWithPath("data.studyMemberDetailResponse.achievement").description("성취도"),
                                    fieldWithPath("data.isAdmin").description("로그인 멤버 관리자 여부"),
                                    fieldWithPath("data.canSendGrape").description("로그인 멤버 포도알 전송 가능 여부")
                            ))
                    );
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않으면 예외가 발생한다.")
        void fail_when_study_not_found() throws Exception {
            Long notExistStudyId = StudyStub.getInvalidStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(get(PATH, notExistStudyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-study-member-detail-full/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("조회할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            ))
                    );
        }

        @Test
        @WithMockMember(id = 0L)
        @DisplayName("[실패] 해당 스터디에 가입한 멤버가 요청한 것이 아니라면 예외가 발생한다.")
        void fail_when_is_not_study_member() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(get(PATH, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("get-study-member-detail-full/fail/is-not-study-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("조회할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            ))
                    );
        }
    }
}