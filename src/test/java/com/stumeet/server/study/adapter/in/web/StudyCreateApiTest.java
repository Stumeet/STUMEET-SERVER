package com.stumeet.server.study.adapter.in.web;

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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.factory.MockMultipartFactory;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.template.ApiTest;

class StudyCreateApiTest extends ApiTest {

	@Nested
	@DisplayName("스터디 생성 API")
	class CreateStudy {

		private final String path = "/api/v1/studies";
		private final MockMultipartFile studyMainImage = MockMultipartFactory.createJpegFile("mainImageFile");

		@Test
		@WithMockMember
		@DisplayName("[성공] 스터디 생성에 성공한다.")
		void successTest() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();

			mockMvc.perform(multipart(path)
					.file(studyMainImage)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(document("create-study/success",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestHeaders(
						headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
							.description("서버로부터 전달받은 액세스 토큰")
					),
					requestParts(
						partWithName("mainImageFile").description("스터디 메인 이미지 파일").optional(),
						partWithName("request").description("스터디 수정 요청 본문")
					),
					requestPartFields( "request",
						fieldWithPath("studyField").description("스터디 분야 ID"),
						fieldWithPath("name").description("스터디 이름"),
						fieldWithPath("intro").description("소개"),
						fieldWithPath("region").description("활동 지역"),
						fieldWithPath("rule").description("규칙").optional(),
						fieldWithPath("startDate").description("시작일"),
						fieldWithPath("endDate").description("종료일"),
						fieldWithPath("meetingTime").description("정기모임 시간"),
						fieldWithPath("meetingRepetitionType").description("정기모임 반복 유형"),
						fieldWithPath("meetingRepetitionDates").description("정기모임 반복 일자 | DAILY 유형의 경우 빈 배열"),
						fieldWithPath("studyTags").description("스터디 태그 | 값이 없는 경우 빈 배열")
					),
					responseFields(
						fieldWithPath("code").description("응답 상태"),
						fieldWithPath("message").description("응답 메시지")
					)));
		}

		@Test
		@WithMockMember
		@DisplayName("[성공] 이미지 파일이 없는 경우에도 요청에 성공한다.")
		void successWithoutImageFile() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();

			mockMvc.perform(multipart(path)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		}

		@Test
		@WithMockMember
		@DisplayName("[성공] 스터디 태그가 빈 배열인 경우 요청에 성공한다.")
		void successWithoutStudyTags() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommandWithoutTags();

			mockMvc.perform(multipart(path)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		}

		@Test
		@WithMockMember
		@DisplayName("[실패] 존재하지 않는 스터디 분야로 요청한 경우 스터디 생성을 실패한다.")
		void failWithNotExistStudyFieldId() throws Exception {
			StudyCreateCommand request = StudyStub.getInvalidFieldStudyCreateCommand();

			mockMvc.perform(multipart(path)
					.file(studyMainImage)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(document("create-study/fail/study-field-not-found",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
						"서버로부터 전달받은 액세스 토큰")),
					responseFields(
						fieldWithPath("code").description("응답 상태"),
						fieldWithPath("message").description("응답 메시지")
					)));
		}

		@Test
		@WithMockMember
		@DisplayName("[실패] 유효하지 않은 반복 일정 값으로 요청한 경우 스터디 생성을 실패한다.")
		void failWithInvalidStudyMeetingSchedule() throws Exception {
			StudyCreateCommand request = StudyStub.getInvalidMeetingScheduleStudyCreateCommand();

			mockMvc.perform(multipart(path)
					.file(studyMainImage)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(document("create-study/fail/invalid-study-meeting-schedule",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
						"서버로부터 전달받은 액세스 토큰")),
					responseFields(
						fieldWithPath("code").description("응답 상태"),
						fieldWithPath("message").description("응답 메시지")
					)));
		}

		@Test
		@WithMockMember
		@DisplayName("[실패] 유효하지 않은 스터디 기간으로 요청한 경우 스터디 생성을 실패한다.")
		void fail_create_study_when_study_period_invalid() throws Exception {
			StudyCreateCommand request = StudyStub.getInvalidStudyPeriodStudyCreateCommand();

			mockMvc.perform(multipart(path)
					.file(studyMainImage)
					.part(new MockPart("request", "", objectMapper.writeValueAsBytes(request), MediaType.APPLICATION_JSON))
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(document("create-study/fail/invalid-study-period",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
						"서버로부터 전달받은 액세스 토큰")),
					responseFields(
						fieldWithPath("code").description("응답 상태"),
						fieldWithPath("message").description("응답 메시지")
					)));
		}
	}
}