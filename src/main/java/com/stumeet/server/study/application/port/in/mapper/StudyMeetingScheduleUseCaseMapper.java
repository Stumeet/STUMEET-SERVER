package com.stumeet.server.study.application.port.in.mapper;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.domain.StudyMeetingSchedule;
import org.springframework.stereotype.Component;

@Component
public class StudyMeetingScheduleUseCaseMapper {

    public StudyMeetingSchedule toDomain(StudyCreateCommand command) {
        return StudyMeetingSchedule.builder()
                        .time(command.meetingTime())
                        .repetition(StudyMeetingSchedule.Repetition.builder()
                                .type(command.meetingRepetitionType())
                                .dates(command.meetingRepetitionDates())
                                .build())
                        .build();
    }
}
