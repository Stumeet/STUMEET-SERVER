package com.stumeet.server.activity.application.port.in.mapper;


import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityUseCaseMapper {

    public ActivityCreateSource toSource(Long studyId, ActivityCreateCommand command, Long id) {
        return ActivityCreateSource.builder()
                .id(null)
                .studyId(studyId)
                .author(ActivityCreateSource.ActivityMemberCreateSource.builder()
                        .id(id)
                        .build())
                .category(ActivityCategory.getByName(command.category()))
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
