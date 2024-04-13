package com.stumeet.server.study.application.port.in.mapper;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.domain.StudyPeriod;
import org.springframework.stereotype.Component;

@Component
public class StudyPeriodUseCaseMapper {

    public StudyPeriod toDomain(StudyCreateCommand command) {
        return StudyPeriod.builder()
                .startDate(command.startDate())
                .endDate(command.endDate())
                .build();
    }
}
