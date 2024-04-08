package com.stumeet.server.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.stumeet.server.file.domain.exception.InvalidFileException;
import com.stumeet.server.template.UnitTest;

class FileUtilTest extends UnitTest {

	@Nested
	@DisplayName("이미지 파일 검증")
	class ExtractExtension {

		@ParameterizedTest
		@CsvSource({
				"file.jpg, jpg",
				"file.jpeg, jpeg",
				"file.png, png"
		})
		@DisplayName("[성공] 유효한 파일 이름의 경우 파일 확장자를 가져오는데 성공한다.")
		void validFileName_extractExtension_success(String fileName, String fileExtension) {
			assertThat(FileUtil.extractExtension(fileName))
					.isEqualTo(fileExtension);
		}

		@ParameterizedTest
		@NullSource
		@ValueSource(strings = "filejpeg")
		@DisplayName("[실패] 유효하지 않은 파일 이름의 경우 파일 확장자를 가져오는데 실패한다.")
		void invalidFileName_extractExtension_fail(String fileName) {
			assertThatThrownBy(() -> FileUtil.extractExtension(fileName))
					.isInstanceOf(InvalidFileException.class);
		}
	}
}