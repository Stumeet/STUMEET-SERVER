package com.stumeet.server.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockMultipartFile;

import com.stumeet.server.file.domain.exception.InvalidFileException;
import com.stumeet.server.stub.MockMultipartStub;
import com.stumeet.server.template.UnitTest;

@DisplayName("File Validator 테스트")
class FileValidatorTest extends UnitTest {

	private final String PARAMETER_NAME = "file";
	private final MockMultipartFile jpegFile = MockMultipartStub.createJpegFile(PARAMETER_NAME);
	private final MockMultipartFile jpgFile = MockMultipartStub.createJpgFile(PARAMETER_NAME);
	private final MockMultipartFile pngFile = MockMultipartStub.createPngFile(PARAMETER_NAME);

	@Nested
	@DisplayName("이미지 파일 검증")
	class ValidateImageFile {

		@Test
		@DisplayName("[성공] 유효한 파일이 주어졌을 때 파일 검증을 성공한다.")
		void validImageFile_validateImageFile_success() {
			assertThatCode(() -> FileValidator.validateImageFile(jpegFile))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(jpgFile))
					.doesNotThrowAnyException();

			assertThatCode(() -> FileValidator.validateImageFile(pngFile))
					.doesNotThrowAnyException();
		}
	}

	@Nested
	@DisplayName("파일 이름 검증")
	class ValidateFileName {

		@Test
		@DisplayName("[성공] 유효한 파일 이름이 주어졌을 때 파일 검증을 성공한다.")
		void validFileName_validateFileName_success() {
			assertThatCode(() -> FileValidator.validateFileName(jpegFile.getOriginalFilename()))
					.doesNotThrowAnyException();
		}

		@ParameterizedTest
		@NullSource
		@ValueSource(strings = "filejpeg")
		@DisplayName("[실패] 유효하지 않은 파일 이름이 주어졌을 때 파일 검증을 실패한다.")
		void invalidFileName_validateFileName_fail(String fileName) {
			assertThatThrownBy(() -> FileValidator.validateFileName(fileName))
					.isInstanceOf(InvalidFileException.class);
		}
	}

	@Nested
	@DisplayName("파일 컨텐트 타입 검증")
	class ValidateFileContentType {

		@ParameterizedTest
		@CsvSource({
				"image/jpeg, jpeg",
				"image/jpeg, jpg",
				"image/png, png"
		})
		@DisplayName("[성공] 유효한 파일 컨텐트 타입이 주어졌을 때 파일 검증을 성공한다.")
		void validFileContent_validateImageContentType_success(String contentType, String extension) {
			assertThatCode(() -> FileValidator.validateImageContentType(contentType, extension))
					.doesNotThrowAnyException();
		}

		@ParameterizedTest
		@CsvSource({
				"null, null",
				"'image/tiff', 'tiff'"
		})
		@DisplayName("[실패] 유효하지 않은 파일 컨텐트 타입이 주어졌을 때 파일 검증을 실패한다.")
		void invalidFileContent_validateImageContentType_fail(String contentType, String extension) {
			assertThatThrownBy(() -> FileValidator.validateImageContentType(contentType, extension))
					.isInstanceOf(InvalidFileException.class);
		}
	}

	@Nested
	@DisplayName("이미지 타입 유효성 반환")
	class ValidateImageType {

		@Test
		@DisplayName("[성공] 유효한 파일이 주어졌을 때 true를 반환한다.")
		void validImageFile_isValidImageFile_success() {
			assertThat(FileValidator.isValidImageFile(jpegFile))
					.isTrue();

			assertThat(FileValidator.isValidImageFile(jpgFile))
					.isTrue();

			assertThat(FileValidator.isValidImageFile(pngFile))
					.isTrue();
		}
	}
}