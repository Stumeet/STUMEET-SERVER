package com.stumeet.server.study.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.stub.RepetitionStub;
import com.stumeet.server.study.domain.exception.InvalidRepetitionDatesException;
import com.stumeet.server.template.UnitTest;

public class RepetitionTest extends UnitTest {

	@Nested
	@DisplayName("[단위 테스트] 스터디 일정 생성")
	class create {

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY일 때, 인수 dates에 빈 리스트를 전달 받았을 경우 생성에 성공한다.")
		void weeklyTypeNullDatesSuccessTest() {
			assertThatCode(() -> Repetition.of(RepetitionType.DAILY, List.of()))
					.doesNotThrowAnyException();

			assertThat(Repetition.of(RepetitionType.DAILY, List.of()))
					.usingRecursiveComparison()
					.isEqualTo(RepetitionStub.getDailyRepetition());
		}

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY일 때, 인수 dates에 빈 리스트가 아닌 값을 전달 받았을 경우 생성에 성공한다.")
		void weeklyTypeInvalidDatesSuccessTest() {
			assertThat(
				Repetition.of(RepetitionType.DAILY, List.of("월", "화", "수", "목", "금", "토", "일"))
					.getDates()
					.isEmpty())
				.isTrue();
		}

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY가 아닐 때, 유효한 값을 전달 받았을 경우 생성에 성공한다.")
		void otherTypesSuccessTest() {
			assertThat(Repetition.of(RepetitionType.WEEKLY, List.of("수", "금")))
					.usingRecursiveComparison()
					.isEqualTo(RepetitionStub.getWeeklyRepetition());

			assertThat(Repetition.of(RepetitionType.MONTHLY, List.of("10", "20", "30")))
					.usingRecursiveComparison()
					.isEqualTo(RepetitionStub.getMonthlyRepetition());
		}

		@Test
		@DisplayName("[실패] 반복 타입이 DAILY가 아닌 경우 반복일이 빈 리스트인 경우 테스트에 실패한다.")
		void invalidDatesFailTest() {
			assertThatThrownBy(() -> Repetition.of(RepetitionType.WEEKLY, List.of()))
					.isInstanceOf(InvalidRepetitionDatesException.class);

			assertThatThrownBy(() -> Repetition.of(RepetitionType.MONTHLY, List.of()))
					.isInstanceOf(InvalidRepetitionDatesException.class);
		}
	}
}
