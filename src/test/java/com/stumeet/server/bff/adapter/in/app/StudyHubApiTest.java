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

class StudyHubApiTest extends ApiTest {
    
    @Nested
    @DisplayName("스터디 홈 화면 조회")
    class StudyMemberDetailFull {
        private static final String PATH = "/api/external/v1/studies/{studyId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 홈 화면 조회에 성공한다.")
        void success() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-home-full/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("스터디 홈 정보"),
                                    fieldWithPath("data.studyDetail").description("스터디 상세 정보"),
                                    fieldWithPath("data.studyDetail.id").description("스터디 ID"),
                                    fieldWithPath("data.studyDetail.field").description("분야"),
                                    fieldWithPath("data.studyDetail.name").description("스터디 이름"),
                                    fieldWithPath("data.studyDetail.tags").description("태그 리스트"),
                                    fieldWithPath("data.studyDetail.intro").description("소개"),
                                    fieldWithPath("data.studyDetail.region").description("지역"),
                                    fieldWithPath("data.studyDetail.rule").description("규칙"),
                                    fieldWithPath("data.studyDetail.image").description("이미지 URL"),
                                    fieldWithPath("data.studyDetail.headcount").description("참여 인원 수"),
                                    fieldWithPath("data.studyDetail.startDate").description("시작 날짜"),
                                    fieldWithPath("data.studyDetail.endDate").description("종료 날짜"),
                                    fieldWithPath("data.studyDetail.meetingTime").description("정기모임 시간"),
                                    fieldWithPath("data.studyDetail.meetingRepetitionType")
                                            .description("정기모임 반복 유형 / `매일`, `매주`, `매달`"),
                                    fieldWithPath("data.studyDetail.meetingRepetitionDates")
                                            .description("정기모임 반복일 / 매주: 요일, 매달: 날짜"),
                                    fieldWithPath("data.studyDetail.isFinished").description("종료 여부"),
                                    fieldWithPath("data.studyDetail.isDeleted").description("삭제 여부"),
                                    fieldWithPath("data.activityNotice").description("최근 공지 활동"),
                                    fieldWithPath("data.activityNotice.id").description("활동 ID"),
                                    fieldWithPath("data.activityNotice.studyId").description("활동 연관 스터디 ID"),
                                    fieldWithPath("data.activityNotice.studyName").description("활동 연관 스터디명"),
                                    fieldWithPath("data.activityNotice.category").description("활동 유형"),
                                    fieldWithPath("data.activityNotice.title").description("활동 제목"),
                                    fieldWithPath("data.activityNotice.content").description("활동 내용"),
                                    fieldWithPath("data.activityNotice.startDate").description("활동 시작일"),
                                    fieldWithPath("data.activityNotice.endDate").description("활동 종료일"),
                                    fieldWithPath("data.activityNotice.location").description("장소"),
                                    fieldWithPath("data.activityNotice.author.memberId").description("활동 작성자 ID"),
                                    fieldWithPath("data.activityNotice.author.name").description("활동 작성자 이름"),
                                    fieldWithPath("data.activityNotice.author.profileImageUrl").description("활동 작성자 프로필 이미지 URL"),
                                    fieldWithPath("data.activityNotice.createdAt").description("활동 생성일"),
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
                    .andDo(document("get-study-home-full/fail/study-not-found",
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
}