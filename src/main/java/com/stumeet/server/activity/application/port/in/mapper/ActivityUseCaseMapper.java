package com.stumeet.server.activity.application.port.in.mapper;


import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityUseCaseMapper {

    public ActivityConstructCommand toConstructCommand(Long studyId, ActivityCreateCommand command, Long id) {
        return ActivityConstructCommand.builder()
                .id(null)
                .studyId(studyId)
                .author(ActivityConstructCommand.ActivityMemberConstructCommand.builder()
                        .id(id)
                        .build())
                .category(command.category())
                .title(command.title())
                .content(command.content())
                .isNotice(command.isNotice())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .createdAt(LocalDateTime.now())
                .location(command.location())
                .build();
    }
}
