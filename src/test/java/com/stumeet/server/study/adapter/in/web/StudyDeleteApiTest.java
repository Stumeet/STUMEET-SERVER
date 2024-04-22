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

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class StudyDeleteApiTest extends ApiTest {

	@Nested
	@DisplayName("스터디 삭제 API")
	class DeleteStudy {

		@Test
		@WithMockMember
		@DisplayName("[성공] 스터디 삭제를 성공한다.")
		void success_delete_study() throws Exception {
			mockMvc.perform(delete("/api/v1/studies/{id}", StudyStub.getStudyId())
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
				.andExpect(status().isOk())
				.andDo(document("delete-study/success",
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

		@Test
		@WithMockMember(id = 3)
		@DisplayName("[실패] 삭제를 시도하는 멤버가 스터디 멤버가 아닌 경우 스터디 삭제에 실패한다.")
		void fail_to_delete_when_member_not_joined_study() throws Exception {
			mockMvc.perform(delete("/api/v1/studies/{id}", StudyStub.getStudyId())
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
				.andExpect(status().isForbidden())
				.andDo(document("delete-study/fail/member-not-admin",
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

		@Test
		@WithMockMember(id = 2)
		@DisplayName("[실패] 삭제를 시도하는 멤버가 관리자가 아닌 경우 스터디 삭제에 실패한다.")
		void fail_to_delete_when_study_member_is_not_admin() throws Exception {
			mockMvc.perform(delete("/api/v1/studies/{id}", StudyStub.getStudyId())
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
				.andExpect(status().isForbidden())
				.andDo(document("delete-study/fail/member-not-joined_study",
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