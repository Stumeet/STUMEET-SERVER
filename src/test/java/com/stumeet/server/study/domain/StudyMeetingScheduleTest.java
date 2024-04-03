package com.stumeet.server.study.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.study.domain.exception.InvalidRepetitionDatesException;
import com.stumeet.server.template.UnitTest;

class StudyMeetingScheduleTest extends UnitTest {

	private final LocalTime meetingTime = LocalTime.of(18, 30);

	@Nested
	@DisplayName("[단위 테스트] 스터디 일정 생성")
	class create {

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY일 때, 인수 dates에 null을 전달 받았을 경우 생성에 성공한다.")
		void weeklyTypeNullDatesSuccessTest() {
			StudyMeetingSchedule.Repetition dailyRepetition = StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.DAILY)
					.dates(null)
					.build();

			assertThatCode(() -> StudyMeetingSchedule.builder()
					.time(meetingTime)
					.repetition(dailyRepetition)
					.build())
					.doesNotThrowAnyException();
		}

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY일 때, 인수 dates에 null이 아닌 값을 전달 받았을 경우 생성에 성공한다.")
		void weeklyTypeInvalidDatesSuccessTest() {
			StudyMeetingSchedule.Repetition dailyRepetitionWithNonNullDates = StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.DAILY)
					.dates(List.of("월", "화", "수", "목", "금", "토", "일"))
					.build();

			assertThat(StudyMeetingSchedule.builder()
					.time(meetingTime)
					.repetition(dailyRepetitionWithNonNullDates)
					.build()
					.getRepetitionDates())
					.isNull();
		}

		@Test
		@DisplayName("[성공] 반복 유형이 DAILY가 아닐 때, 유효한 값을 전달 받았을 경우 생성에 성공한다.")
		void otherTypesSuccessTest() {
			StudyMeetingSchedule.Repetition weeklyRepetition = StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.WEEKLY)
					.dates(List.of("수", "금"))
					.build();

			assertThatCode(() -> StudyMeetingSchedule.builder()
					.time(meetingTime)
					.repetition(weeklyRepetition)
					.build())
					.doesNotThrowAnyException();

			StudyMeetingSchedule.Repetition monthlyRepetition = StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.MONTHLY)
					.dates(List.of("10", "20", "30"))
					.build();

			assertThatCode(() -> StudyMeetingSchedule.builder()
					.time(meetingTime)
					.repetition(monthlyRepetition)
					.build())
					.doesNotThrowAnyException();
		}

		@Test
		@DisplayName("[실패] 반복 타입이 DAILY가 아닌 경우 반복일이 null이면 테스트에 실패한다.")
		void invalidDatesFailTest() {
			assertThatThrownBy(() -> StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.WEEKLY)
					.dates(null)
					.build())
					.isInstanceOf(InvalidRepetitionDatesException.class);

			assertThatThrownBy(() -> StudyMeetingSchedule.Repetition.builder()
					.type(RepetitionType.MONTHLY)
					.dates(null)
					.build())
					.isInstanceOf(InvalidRepetitionDatesException.class);
		}
	}
}