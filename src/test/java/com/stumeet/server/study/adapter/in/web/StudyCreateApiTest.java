package com.stumeet.server.study.adapter.in.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.snippet.Attributes.key;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.template.ApiTest;

class StudyCreateApiTest extends ApiTest {

	@Nested
	@DisplayName("스터디 생성하기")
	class CreateStudy {

		private final String path = "/api/v1/studies";

		@Test
		@WithMockMember
		@DisplayName("[성공] 스터디 생성에 성공한다.")
		void successTest() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();

			RequestPostProcessor postMethod = http -> {
				http.setMethod("POST");
				return http;
			};
			mockMvc.perform(multipart(path)
							.file((MockMultipartFile) request.image())
							.with(postMethod)
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
							.queryParam("studyFieldId", String.valueOf(request.studyFieldId()))
							.queryParam("name", request.name())
							.queryParam("intro", request.intro())
							.queryParam("region", request.region())
							.queryParam("rule", request.rule())
							.queryParam("startDate", String.valueOf(request.startDate()))
							.queryParam("endDate", String.valueOf(request.endDate()))
							.queryParam("meetingTime", String.valueOf(request.meetingTime()))
							.queryParam("meetingRepetitionType", request.meetingRepetitionType().toString())
							.queryParam("meetingRepetitionDates", request.meetingRepetitionDates().toArray(String[]::new))
							.queryParam("studyTags", request.studyTags().toArray(String[]::new))
							.contentType(MediaType.MULTIPART_FORM_DATA)
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
									partWithName("image").description("스터디 메인 이미지 파일").optional()
							),
							queryParameters(
									parameterWithName("studyFieldId").description("스터디 분야 ID")
											.attributes(key("constraint").value("NotNull, 스터디 분야 ID를 입력해주세요.")),
									parameterWithName("name").description("스터디 이름")
											.attributes(key("constraint").value("NotBlank, 이름을 입력해주세요.")),
									parameterWithName("intro").description("소개")
											.attributes(key("constraint").value("NotBlank, 소개글을 입력해주세요.")),
									parameterWithName("region").description("활동 지역")
											.attributes(key("constraint").value("NotBlank, 지역을 입력해주세요.")),
									parameterWithName("rule").description("규칙").optional()
											.attributes(key("constraint").value("NullOrNotBlank, 규칙은 공백일 수 없습니다.")),
									parameterWithName("startDate").description("시작일")
											.attributes(key("constraint").value("NotNull, 시작일을 입력해주세요.")),
									parameterWithName("endDate").description("종료일")
											.attributes(key("constraint").value("NotNull, 종료일을 입력해주세요.")),
									parameterWithName("meetingTime").description("정기모임 시간")
											.attributes(key("constraint").value("NotNull, 정기모임 시간을 입력해주세요.")),
									parameterWithName("meetingRepetitionType").description("정기모임 반복 유형")
											.attributes(key("constraint").value("NotNull, 정기모임 반복 유형을 입력해주세요.")),
									parameterWithName("meetingRepetitionDates").description("정기모임 반복 일자").optional(),
									parameterWithName("studyTags").description("스터디 태그").optional()
							),
							responseFields(
									fieldWithPath("code").description("응답 상태"),
									fieldWithPath("message").description("응답 메시지"),
									fieldWithPath("data.id").description("스터디 ID"),
									fieldWithPath("data.field").description("분야"),
									fieldWithPath("data.name").description("스터디 이름"),
									fieldWithPath("data.tags").description("태그 리스트"),
									fieldWithPath("data.intro").description("소개"),
									fieldWithPath("data.region").description("활동 지역"),
									fieldWithPath("data.rule").description("규칙"),
									fieldWithPath("data.image").description("이미지 URL"),
									fieldWithPath("data.headcount").description("참여 인원 수"),
									fieldWithPath("data.startDate").description("시작일"),
									fieldWithPath("data.endDate").description("종료일"),
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
		@DisplayName("[성공] 이미지 파일이 없어도 요청에 성공한다.")
		void successWithoutImageFile() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();

			RequestPostProcessor postMethod = http -> {
				http.setMethod("POST");
				return http;
			};
			mockMvc.perform(multipart(path)
							.with(postMethod)
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
							.queryParam("studyFieldId", String.valueOf(request.studyFieldId()))
							.queryParam("name", request.name())
							.queryParam("intro", request.intro())
							.queryParam("region", request.region())
							.queryParam("rule", request.rule())
							.queryParam("startDate", String.valueOf(request.startDate()))
							.queryParam("endDate", String.valueOf(request.endDate()))
							.queryParam("meetingTime", String.valueOf(request.meetingTime()))
							.queryParam("meetingRepetitionType", request.meetingRepetitionType().toString())
							.queryParam("meetingRepetitionDates", request.meetingRepetitionDates().toArray(String[]::new))
							.queryParam("studyTags", request.studyTags().toArray(String[]::new))
							.contentType(MediaType.MULTIPART_FORM_DATA)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated());
		}

		@Test
		@WithMockMember
		@DisplayName("[실패] 존재하지 않는 스터디 분야의 ID로 요청한 경우 스터디 생성을 실패한다.")
		void failWithNotExistStudyFieldId() throws Exception {
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();
			int invalidStudyFieldId = 0;

			RequestPostProcessor postMethod = http -> {
				http.setMethod("POST");
				return http;
			};
			mockMvc.perform(multipart(path)
							.file((MockMultipartFile) request.image())
							.with(postMethod)
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
							.queryParam("studyFieldId", String.valueOf(invalidStudyFieldId))
							.queryParam("name", request.name())
							.queryParam("intro", request.intro())
							.queryParam("region", request.region())
							.queryParam("rule", request.rule())
							.queryParam("startDate", String.valueOf(request.startDate()))
							.queryParam("endDate", String.valueOf(request.endDate()))
							.queryParam("meetingTime", String.valueOf(request.meetingTime()))
							.queryParam("meetingRepetitionType", request.meetingRepetitionType().toString())
							.queryParam("meetingRepetitionDates", request.meetingRepetitionDates().toArray(String[]::new))
							.queryParam("studyTags", request.studyTags().toArray(String[]::new))
							.contentType(MediaType.MULTIPART_FORM_DATA)
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
			StudyCreateCommand request = StudyStub.getStudyCreateCommand();

			RequestPostProcessor postMethod = http -> {
				http.setMethod("POST");
				return http;
			};
			mockMvc.perform(multipart(path)
							.file((MockMultipartFile) request.image())
							.with(postMethod)
							.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
							.queryParam("studyFieldId", String.valueOf(request.studyFieldId()))
							.queryParam("name", request.name())
							.queryParam("intro", request.intro())
							.queryParam("region", request.region())
							.queryParam("rule", request.rule())
							.queryParam("startDate", String.valueOf(request.startDate()))
							.queryParam("endDate", String.valueOf(request.endDate()))
							.queryParam("meetingTime", String.valueOf(request.meetingTime()))
							.queryParam("meetingRepetitionType", request.meetingRepetitionType().toString())
							.queryParam("studyTags", request.studyTags().toArray(String[]::new))
							.contentType(MediaType.MULTIPART_FORM_DATA)
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
	}
}