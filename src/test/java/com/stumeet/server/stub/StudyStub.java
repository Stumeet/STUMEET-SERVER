package com.stumeet.server.stub;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import org.springframework.mock.web.MockMultipartFile;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
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

    public static StudyCreateCommand getStudyCreateCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        return new StudyCreateCommand(
                image,
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

    public static StudyCreateCommand getInvalidFieldStudyCreateCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        return new StudyCreateCommand(
                image,
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

    public static StudyCreateCommand getInvalidMeetingSchduleStudyCreateCommand() {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg",
                "test".getBytes());
        return new StudyCreateCommand(
                image,
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
}
