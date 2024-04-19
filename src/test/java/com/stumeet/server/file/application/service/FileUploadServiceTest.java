package com.stumeet.server.file.application.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.factory.MockMultipartFactory;
import com.stumeet.server.template.IntegrationTest;

class FileUploadServiceTest extends IntegrationTest {

	private final String PARAMETER_NAME = "file";
	private final MockMultipartFile jpegFile = MockMultipartFactory.createJpegFile(PARAMETER_NAME);
	private final MockMultipartFile jpgFile = MockMultipartFactory.createJpgFile(PARAMETER_NAME);
	private final MockMultipartFile pngFile = MockMultipartFactory.createPngFile(PARAMETER_NAME);

	@Autowired
	private FileUploadService fileUploadService;


	@Nested
	@DisplayName("[통합 테스트] 유저 프로필 이미지 파일 업로드")
	class UploadUserProfileImageFile {

		@Test
		@DisplayName("[성공] 유효한 파일이 주어졌을 때 파일 업로드에 성공한다.")
		void uploadUserProfileImage_success() {
			assertThatCode(() -> fileUploadService.uploadUserProfileImage(MemberStub.getMemberId(), jpegFile))
					.doesNotThrowAnyException();

			assertThatCode(() -> fileUploadService.uploadUserProfileImage(MemberStub.getMemberId(), jpgFile))
					.doesNotThrowAnyException();

			assertThatCode(() -> fileUploadService.uploadUserProfileImage(MemberStub.getMemberId(), pngFile))
					.doesNotThrowAnyException();
		}
	}
}