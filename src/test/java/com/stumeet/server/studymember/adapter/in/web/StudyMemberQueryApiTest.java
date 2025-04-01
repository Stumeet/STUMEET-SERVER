package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudyMemberQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 멤버 전체 조회")
    class GetStudyMembers {

        private final String path = "/api/v1/studies/{studyId}/members";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 멤버를 조회할 수 있다.")
        void SuccessTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-members/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.studyMembers[]").description("스터디 멤버 목록"),
                                    fieldWithPath("data.studyMembers[].id").description("스터디 멤버 ID"),
                                    fieldWithPath("data.studyMembers[].name").description("스터디 멤버 이름"),
                                    fieldWithPath("data.studyMembers[].image").description("스터디 멤버 프로필 이미지"),
                                    fieldWithPath("data.studyMembers[].region").description("스터디 멤버 지역"),
                                    fieldWithPath("data.studyMembers[].profession").description("스터디 멤버 분야"),
                                    fieldWithPath("data.studyMembers[].isAdmin").description("스터디 관리자 여부")
                            )));
        }

        @Test
        @WithMockMember(id = 0)
        @DisplayName("[실패] 해당 스터디에 가입한 멤버가 요청한 것이 아니라면 예외가 발생한다.")
        void NotJoinedMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("get-study-members/fail/not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않으면 예외가 발생한다.")
        void NotExistStudyTest() throws Exception {
            Long notExistStudyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(get(path, notExistStudyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-study-members/fail/not-exist-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }

    @Nested
    @DisplayName("단일 스터디 멤버 상세 조회")
    class GetStudyMemberDetail {
        private static final String PATH = "/api/v1/studies/{studyId}/members/{memberId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 단일 스터디 멤버 상세 조회에 성공한다.")
        void success() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(get(PATH, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-member-detail/success",
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
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 멤버 상세 정보"),
                                    fieldWithPath("data.id").description("멤버 ID"),
                                    fieldWithPath("data.name").description("스터디 멤버 이름"),
                                    fieldWithPath("data.image").description("스터디 멤버 프로필 이미지"),
                                    fieldWithPath("data.region").description("스터디 멤버 지역"),
                                    fieldWithPath("data.profession").description("스터디 멤버 분야"),
                                    fieldWithPath("data.achievement").description("성취도")
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
                    .andDo(document("get-study-member-detail/fail/study-not-found",
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
                    .andDo(document("get-study-member-detail/fail/is-not-study-member",
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

    @Nested
    @DisplayName("내 스터디 멤버 상세 정보 조회")
    class GetMyStudyMemberDetail {
        private static final String PATH = "/api/v1/studies/{studyId}/members/me";

        @Test
        @WithMockMember
        @DisplayName("[성공] 활성화된 스터디의 내 스터디 멤버 상세 조회를 성공한다.")
        void success_active_study() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.isReviewCompleted").isEmpty())
                    .andDo(document("get-my-study-member-detail/success/active-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 멤버 관리자 여부"),
                                    fieldWithPath("data.isAdmin").description("관리자 여부"),
                                    fieldWithPath("data.canSendGrape").description("포도알 전송 가능 여부"),
                                    fieldWithPath("data.isReviewCompleted").description("리뷰 작성 완료 여부 "
                                            + "`null`: 미완료 스터디")
                            ))
                    );
        }

        @Test
        @WithMockMember
        @DisplayName("[성공] 완료된 스터디의 내 스터디 멤버 상세 조회를 성공한다.")
        void success_finished_study() throws Exception {
            Long studyId = StudyStub.getFinishedStudyId();

            mockMvc.perform(get(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.canSendGrape").value(false))
                    .andDo(document("get-my-study-member-detail/success/finished-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 멤버 관리자 여부"),
                                    fieldWithPath("data.isAdmin").description("관리자 여부"),
                                    fieldWithPath("data.canSendGrape").description("포도알 전송 가능 여부"),
                                    fieldWithPath("data.isReviewCompleted").description("리뷰 작성 완료 여부 "
                                            + "`false`: 리뷰 미완료 / `true`: 리뷰 완료")
                            ))
                    );
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[성공] 요청자가 스터디 멤버가 아닌 경우 내 스터디 멤버 상세 정보 조회에 실패한다.")
        void fail_when_not_joined_study_member() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("get-my-study-member-detail/fail/not-joined-study-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            ))
                    );
        }

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디가 존재하지 않을 경우 내 스터디 멤버 상세 정보 조회에 실패한다.")
        void fail_when_study_not_exist() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(get(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-my-study-member-detail/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            ))
                    );
        }
    }

    @Nested
    @DisplayName("특정 멤버의 스터디 멤버 리뷰 상태 조회")
    class GetUnreviewedStudyMembersForMember {
        private final String path = "/api/v1/studies/{studyId}/members/review-status";

        @Test
        @WithMockMember
        @DisplayName("[성공] 특정 멤버의 스터디 멤버 리뷰 상태 목록을 조회할 수 있다.")
        void success() throws Exception {
            Long studyId = StudyStub.getFinishedStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-member-review-status/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 멤버 리뷰 상태 목록"),
                                    fieldWithPath("data[].studyMemberId").description("스터디 멤버 ID"),
                                    fieldWithPath("data[].memberId").description("멤버 ID"),
                                    fieldWithPath("data[].name").description("멤버 이름"),
                                    fieldWithPath("data[].region").description("멤버가 활동하는 지역"),
                                    fieldWithPath("data[].profession").description("멤버의 직업"),
                                    fieldWithPath("data[].image").description("멤버 프로필 이미지 URL"),
                                    fieldWithPath("data[].isReviewed").description("리뷰가 작성되었는지 여부")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않으면, 특정 멤버의 스터디 멤버 리뷰 상태 목록 조회에 실패한다.")
        void fail_when_study_not_found() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-study-member-review-status/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 완료된 스터디가 아니라면, 특정 멤버의 스터디 멤버 리뷰 상태 목록 조회에 실패한다.")
        void fail_when_study_not_legacy() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isConflict())
                    .andDo(document("get-study-member-review-status/fail/study-not-legacy",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 요청 멤버가 해당 스터디의 멤버가 아니라면, 특정 멤버의 스터디 멤버 리뷰 상태 목록 조회에 실패한다.")
        void fail_when_member_not_study_member() throws Exception {
            Long studyId = StudyStub.getFinishedStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("get-study-member-review-status/fail/member-not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
                                            "서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}