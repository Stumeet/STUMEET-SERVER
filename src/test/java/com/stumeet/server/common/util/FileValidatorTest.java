package com.stumeet.server.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.common.exception.model.InvalidFileException;
import com.stumeet.server.stub.MockMultipartFileStub;
import com.stumeet.server.template.UnitTest;

@DisplayName("File Validator 테스트")
class FileValidatorTest extends UnitTest {

	@Nested
	@DisplayName("이미지 파일 검증")
	class ValidateImageFile {

		@Test
		@DisplayName("[성공] 유효한 파일이 주어졌을 때 파일 검증을 성공한다.")
		void validImageFile_validateImageFile_success() {
			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getJpegFile()))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getJpgFile()))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getPngFile()))
					.doesNotThrowAnyException();
		}
	}

	@Nested
	@DisplayName("파일 이름 검증")
	class ValidateFileName {

		@Test
		@DisplayName("[성공] 유효한 파일 이름이 주어졌을 때 파일 검증을 성공한다.")
		void validFileName_validateFileName_success() {
			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getJpegFile()))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getJpgFile()))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(MockMultipartFileStub.getPngFile()))
					.doesNotThrowAnyException();
		}

		@Test
		@DisplayName("[실패] 유효하지 않은 파일 이름이 주어졌을 때 파일 검증을 실패한다.")
		void invalidFileName_validateFileName_fail() {
			assertThatThrownBy(() -> FileValidator.validateFileName(null))
					.isInstanceOf(InvalidFileException.class);

			String fileNameNotContainDot = "filejpeg";
			assertThatThrownBy(() -> FileValidator.validateFileName(fileNameNotContainDot))
					.isInstanceOf(InvalidFileException.class);
		}
	}

	@Nested
	@DisplayName("파일 컨텐트 타입 검증")
	class ValidateFileContentType {

		@Test
		@DisplayName("[성공] 유효한 파일 컨텐트 타입이 주어졌을 때 파일 검증을 성공한다.")
		void validFileContent_validateImageContentType_success() {
			assertThatCode(() -> FileValidator.validateImageContentType("image/jpeg", "jpeg"))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageContentType("image/jpeg", "jpg"))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageContentType("image/png", "png"))
					.doesNotThrowAnyException();
		}

		@Test
		@DisplayName("[실패] 유효하지 않은 파일 컨텐트 타입이 주어졌을 때 파일 검증을 실패한다.")
		void invalidFileContent_validateImageContentType_fail() {
			assertThatThrownBy(() -> FileValidator.validateImageContentType(null, null))
					.isInstanceOf(InvalidFileException.class);

			assertThatThrownBy(() -> FileValidator.validateImageContentType("image/tiff", "tiff"))
					.isInstanceOf(InvalidFileException.class);
		}
	}

	@Nested
	@DisplayName("이미지 타입 유효성 반환")
	class ValidateImageType {

		@Test
		@DisplayName("[성공] 유효한 파일이 주어졌을 때 true를 반환한다.")
		void validImageFile_isValidImageFile_success() {
			assertThat(FileValidator.isValidImageFile(MockMultipartFileStub.getJpegFile()))
					.isTrue();

			assertThat(FileValidator.isValidImageFile(MockMultipartFileStub.getJpgFile()))
					.isTrue();

			assertThat(FileValidator.isValidImageFile(MockMultipartFileStub.getPngFile()))
					.isTrue();
		}
	}
}