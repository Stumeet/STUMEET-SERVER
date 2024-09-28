package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityStub;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActivityQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 단일 조회 API")
    class GetById {

        private static final String PATH = "/api/v1/studies/{studyId}/activities/{activityId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 활동 단일 조회에 성공한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value(SuccessCode.GET_SUCCESS.getMessage()))
                    .andDo(document("get-activity-by-id/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.id").description("활동 ID"),
                                    fieldWithPath("data.category").description("활동 유형"),
                                    fieldWithPath("data.title").description("활동 제목"),
                                    fieldWithPath("data.content").description("활동 내용"),
                                    fieldWithPath("data.imageUrl[].id").description("활동 이미지의 아이디"),
                                    fieldWithPath("data.imageUrl[].imageUrl").description("활동 이미지의 URL"),
                                    fieldWithPath("data.author.memberId").description("활동 작성자 ID"),
                                    fieldWithPath("data.author.name").description("활동 작성자 이름"),
                                    fieldWithPath("data.author.profileImageUrl").description("활동 작성자 프로필 이미지 URL"),
                                    fieldWithPath("data.participants[].memberId").description("참여자 ID"),
                                    fieldWithPath("data.participants[].name").description("참여자 이름"),
                                    fieldWithPath("data.participants[].profileImageUrl").description("참여자 프로필 이미지 URL"),
                                    fieldWithPath("data.status").description("나의 활동 상태"),
                                    fieldWithPath("data.startDate").description("활동 시작일"),
                                    fieldWithPath("data.endDate").description("활동 종료일"),
                                    fieldWithPath("data.location").description("장소"),
                                    fieldWithPath("data.link").description("링크"),
                                    fieldWithPath("data.createdAt").description("활동 생성일"),
                                    fieldWithPath("data.isAuthor").description("작성자 여부"),
                                    fieldWithPath("data.isAdmin").description("관리자 여부")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 조회에 실패한다.")
        void studyNotFoundTest() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-by-id/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 스터디에 가입하지 않은 사용자인 경우 조회에 실패한다.")
        void notJoinedStudyTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_MEMBER_NOT_JOINED_EXCEPTION.getMessage()))
                    .andDo(document("get-activity-by-id/fail/not-joined-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 활동이 존재하지 않는 경우 조회에 실패한다.")
        void notFoundActivityTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getInvalidActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.ACTIVITY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-by-id/fail/activity-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }
    }

    @Nested
    @DisplayName("활동 목록 상세 조회 API")
    class GetDetailsByCondition {
        private static final String PATH = "/api/v1/studies/activities/detail";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 활동 목록 상세 조회에 성공한다.")
        void success() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "100")
                            .param("page", "0")
                            .param("isNotice", "true")
                            .param("studyId", StudyStub.getStudyId().toString())
                            .param("category", ActivityCategory.MEET.name())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-activity-details-by-condition/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수"),
                                    parameterWithName("page").description("조회할 페이지 번호"),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.items[]").description("활동 상세 목록"),
                                    fieldWithPath("data.items[].id").description("활동 ID"),
                                    fieldWithPath("data.items[].category").description("활동 유형"),
                                    fieldWithPath("data.items[].title").description("활동 제목"),
                                    fieldWithPath("data.items[].content").description("활동 내용"),
                                    fieldWithPath("data.items[].startDate").description("활동 시작일"),
                                    fieldWithPath("data.items[].endDate").description("활동 종료일"),
                                    fieldWithPath("data.items[].location").description("장소"),
                                    fieldWithPath("data.items[].author.memberId").description("활동 작성자 ID"),
                                    fieldWithPath("data.items[].author.name").description("활동 작성자 이름"),
                                    fieldWithPath("data.items[].author.profileImageUrl").description("활동 작성자 프로필 이미지 URL"),
                                    fieldWithPath("data.items[].createdAt").description("활동 생성일"),
                                    fieldWithPath("data.pageInfo").description("페이지 메타 정보"),
                                    fieldWithPath("data.pageInfo.totalPages").description("전체 페이지 수"),
                                    fieldWithPath("data.pageInfo.totalElements").description("전체 요소 수"),
                                    fieldWithPath("data.pageInfo.currentPage").description("현재 페이지"),
                                    fieldWithPath("data.pageInfo.pageSize").description("페이지 크기")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 존재하지 않는 스터디 id로 요청하는 경우 조회에 실패한다.")
        void fail_when_study_id_not_found() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "2")
                            .param("page", "0")
                            .param("studyId", StudyStub.getInvalidStudyId().toString())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-activity-details-by-condition/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수"),
                                    parameterWithName("page").description("조회할 페이지 번호"),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 유효하지 않은 활동 유형으로 요청하는 경우 조회에 실패한다.")
        void fail_when_activity_category_not_found() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "2")
                            .param("page", "0")
                            .param("category", ActivityStub.getInvalidActivityCategoryName())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isBadRequest())
                    .andDo(document("get-activity-details-by-condition/fail/activity-category-invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수"),
                                    parameterWithName("page").description("조회할 페이지 번호"),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }
    }

    @Nested
    @DisplayName("활동 목록 간략 조회 API")
    class GetBriefsByCondition {
        private static final String PATH = "/api/v1/studies/activities/brief";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 활동 목록 간략 조회에 성공한다.")
        void success() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "100")
                            .param("page", "0")
                            .param("isNotice", "true")
                            .param("category", ActivityCategory.MEET.name())
                            .param("studyId", StudyStub.getStudyId().toString())
                            .param("memberId", MemberStub.getMemberId().toString())
                            .param("fromDate", "2024-04-01T00:00:00")
                            .param("toDate", "2024-04-30T00:00:00")
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-activity-briefs-by-condition/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.items[]").description("활동 상세 목록"),
                                    fieldWithPath("data.items[].id").description("활동 ID"),
                                    fieldWithPath("data.items[].category").description("활동 유형"),
                                    fieldWithPath("data.items[].title").description("활동 제목"),
                                    fieldWithPath("data.items[].startDate").description("활동 시작일"),
                                    fieldWithPath("data.items[].endDate").description("활동 종료일"),
                                    fieldWithPath("data.items[].location").description("장소"),
                                    fieldWithPath("data.items[].status").description("내 활동 상태"),
                                    fieldWithPath("data.items[].createdAt").description("활동 생성일"),
                                    fieldWithPath("data.pageInfo").description("페이지 메타 정보"),
                                    fieldWithPath("data.pageInfo.totalPages").description("전체 페이지 수"),
                                    fieldWithPath("data.pageInfo.totalElements").description("전체 요소 수"),
                                    fieldWithPath("data.pageInfo.currentPage").description("현재 페이지"),
                                    fieldWithPath("data.pageInfo.pageSize").description("페이지 크기")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 불완전한 페이지네이션 요청변수로 요청하는 경우 조회에 실패한다.")
        void fail_when_pagination_parameters_is_incomplete() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "5")
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isBadRequest())
                    .andDo(document("get-activity-briefs-by-condition/fail/pagination-incomplete",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 0보다 작은 페이지네이션 요청변수로 요청하는 경우 조회에 실패한다.")
        void fail_when_pagination_parameters_is_smaller_than_zero() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("size", "-1")
                            .param("page", "-1")
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isBadRequest())
                    .andDo(document("get-activity-briefs-by-condition/fail/pagination-invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 존재하지 않는 스터디 id로 요청하는 경우 조회에 실패한다.")
        void fail_when_study_id_not_found() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("studyId", StudyStub.getInvalidStudyId().toString())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-activity-briefs-by-condition/fail/study-not-found",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 유효하지 않은 활동 유형으로 요청하는 경우 조회에 실패한다.")
        void fail_when_activity_category_not_found() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("category", ActivityStub.getInvalidActivityCategoryName())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isBadRequest())
                    .andDo(document("get-activity-briefs-by-condition/fail/activity-category-invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 시작기준일이 종료기준일보다 이후인 경우 조회에 실패한다.")
        void fail_when_fromDate_is_after_toDate() throws Exception {
            mockMvc.perform(get(PATH)
                            .param("fromDate", "2024-06-01T00:00:00")
                            .param("toDate", "2024-05-01T00:00:00")
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isBadRequest())
                    .andDo(document("get-activity-briefs-by-condition/fail/period_invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            queryParameters(
                                    parameterWithName("size").description("페이지당 결과 수").optional(),
                                    parameterWithName("page").description("조회할 페이지 번호").optional(),
                                    parameterWithName("isNotice").description("공지사항 여부 (true/false)").optional(),
                                    parameterWithName("studyId").description("특정 스터디 ID").optional(),
                                    parameterWithName("memberId").description("멤버 ID (생략 시 로그인 멤버 id로 조회)").optional(),
                                    parameterWithName("category").description("활동 유형 (DEFAULT/MEET/ASSIGNMENT)").optional(),
                                    parameterWithName("fromDate").description("YYYY-MM-DDThh:mm:ss").optional(),
                                    parameterWithName("toDate").description("YYYY-MM-DDThh:mm:ss").optional()
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }
    }
}