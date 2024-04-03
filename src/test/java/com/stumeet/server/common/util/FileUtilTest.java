package com.stumeet.server.common.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.common.exception.model.InvalidFileException;
import com.stumeet.server.template.UnitTest;

class FileUtilTest extends UnitTest {

	@Nested
	@DisplayName("이미지 파일 검증")
	class ExtractExtension {

		@Test
		@DisplayName("[성공] 유효한 파일 이름의 경우 파일 확장자를 가져오는데 성공한다.")
		void validFileName_extractExtension_success() {
			assertThat(FileUtil.extractExtension("file.jpg"))
					.isEqualTo("jpg");

			assertThat(FileUtil.extractExtension("file.jpeg"))
					.isEqualTo("jpeg");

			assertThat(FileUtil.extractExtension("file.png"))
					.isEqualTo("png");
		}

		@Test
		@DisplayName("[실패] 유효하지 않은 파일 이름의 경우 파일 확장자를 가져오는데 실패한다.")
		void invalidFileName_extractExtension_fail() {
			assertThatThrownBy(() -> FileUtil.extractExtension(null))
					.isInstanceOf(InvalidFileException.class);

			assertThatThrownBy(() -> FileUtil.extractExtension("filejpeg"))
					.isInstanceOf(InvalidFileException.class);
		}
	}
}