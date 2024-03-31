package com.stumeet.server.study.adapter.in.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class StudyQueryApiTest extends ApiTest {

	@Nested
	@DisplayName("스터디 상세 정보 가져오기")
	class GetStudyDetail {

		@Test
		@Transactional
		@WithMockMember
		@DisplayName("[성공] 스터디 상세 정보 조회를 성공한다.")
		void successTest() throws Exception {
			mockMvc.perform(get("/api/v1/studies/{id}", StudyStub.getStudyId())
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
					.andExpect(status().isOk())
					.andDo(document("get-study-detail/success",
							preprocessRequest(prettyPrint()),
							preprocessResponse(prettyPrint()),
							requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
									"서버로부터 전달받은 액세스 토큰")),
							pathParameters(
									parameterWithName("id").description("스터디 ID")
							),
							responseFields(
									fieldWithPath("code").description("응답 상태"),
									fieldWithPath("message").description("응답 메시지"),
									fieldWithPath("data.id").description("스터디 ID"),
									fieldWithPath("data.field").description("분야"),
									fieldWithPath("data.name").description("스터디 이름"),
									fieldWithPath("data.tags").description("태그 리스트"),
									fieldWithPath("data.intro").description("소개"),
									fieldWithPath("data.region").description("지역"),
									fieldWithPath("data.rule").description("규칙"),
									fieldWithPath("data.image").description("이미지 URL"),
									fieldWithPath("data.headcount").description("참여 인원 수"),
									fieldWithPath("data.startDate").description("시작 날짜"),
									fieldWithPath("data.endDate").description("종료 날짜"),
									fieldWithPath("data.meetingTime").description("정기모임 시간"),
									fieldWithPath("data.meetingRepetitionType")
											.description("정기모임 반복 유형 / `매일`, `매주`, `매달`"),
									fieldWithPath("data.meetingRepetitionDates")
											.description("정기모임 반복일 / 매주: 요일, 매달: 날짜"),
									fieldWithPath("data.isFinished").description("종료 여부"),
									fieldWithPath("data.isDeleted").description("삭제 여부")
							)));
		}

		@Test
		@WithMockMember
		@DisplayName("[실패] 존재하지 않는 스터디의 ID로 요청한 경우 스터디 상세정보 조회를 실패한다.")
		void invalidRequestTest() throws Exception {
			mockMvc.perform(get("/api/v1/studies/{id}", StudyStub.getInvalidStudyId())
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
					.andExpect(status().isNotFound())
					.andDo(document("get-study-detail/fail/not-found",
							preprocessRequest(prettyPrint()),
							preprocessResponse(prettyPrint()),
							requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
									"서버로부터 전달받은 액세스 토큰")),
							pathParameters(
									parameterWithName("id").description("스터디 ID")
							),
							responseFields(
									fieldWithPath("code").description("응답 상태"),
									fieldWithPath("message").description("응답 메시지")
							)));
		}
	}
}