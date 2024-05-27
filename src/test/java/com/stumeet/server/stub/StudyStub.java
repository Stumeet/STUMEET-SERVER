package com.stumeet.server.stub;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.command.StudyUpdateCommand;
import com.stumeet.server.study.domain.RepetitionType;

public class StudyStub {

	private StudyStub() {

	}

	public static Long getStudyId() {
		return 1L;
	}

	public static Long getInvalidStudyId() {
		return 0L;
	}

	public static Long getFutureStudyId() {
		return 2L;
	}

	public static StudyCreateCommand getStudyCreateCommand() {
		return new StudyCreateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of("영어", "회화", "언어 교환")
		);
	}

	public static StudyCreateCommand getStudyCreateCommandWithoutTags() {
		return new StudyCreateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of()
		);
	}

	public static StudyCreateCommand getInvalidFieldStudyCreateCommand() {
		return new StudyCreateCommand(
			"존재하지 않는 분야 값",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of("영어", "회화", "언어 교환")
		);
	}

	public static StudyCreateCommand getInvalidMeetingScheduleStudyCreateCommand() {
		return new StudyCreateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of(),
			List.of("영어", "회화", "언어 교환")
		);
	}

	public static StudyCreateCommand getInvalidStudyPeriodStudyCreateCommand() {
		return new StudyCreateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2999-12-31"),
			LocalDate.parse("2024-01-01"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of(),
			List.of("영어", "회화", "언어 교환")
		);
	}

	public static StudyUpdateCommand getStudyUpdateCommand() {
		return new StudyUpdateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of("영어", "회화", "언어 교환", "어학")
		);
	}

	public static StudyUpdateCommand getStudyUpdateCommandWithoutTags() {
		return new StudyUpdateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of()
		);
	}

	public static StudyUpdateCommand getInvalidFieldStudyUpdateCommand() {
		return new StudyUpdateCommand(
			"존재하지 않는 분야 값",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of("월", "금"),
			List.of("영어", "회화", "언어 교환", "어학")
		);
	}

	public static StudyUpdateCommand getInvalidMeetingScheduleStudyUpdateCommand() {
		return new StudyUpdateCommand(
			"어학",
			"영어 회화 클럽",
			"매주 영어로 대화하며 언어 실력을 향상시키는 스터디 그룹입니다.",
			"부산",
			"주 1회 대면 미팅 및 온라인 토론",
			LocalDate.parse("2024-05-15"),
			LocalDate.parse("2024-12-15"),
			LocalTime.parse("18:30:00"),
			RepetitionType.valueOf("WEEKLY"),
			List.of(),
			List.of("영어", "회화", "언어 교환", "어학")
		);
	}

	public static String getInvalidStudyStatus() {
		return "INVALID_STUDY_STATUS";
	}
}
