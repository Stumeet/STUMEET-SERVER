package com.stumeet.server.activity.domain.model;

import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
import com.stumeet.server.activity.domain.exception.NotExistsActivityCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ActivityCategory {
    DEFAULT(DefaultStatus.NONE) {
        @Override
        public Activity create(ActivityCreateSource command) {
            return Default.builder()
                    .id(command.id())
                    .study(ActivityLinkedStudy.builder().id(command.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(command.author().id())
                            .name(command.author().name())
                            .image(command.author().image())
                            .build()
                    )
                    .category(command.category())
                    .title(command.title())
                    .content(command.content())
                    .isNotice(command.isNotice())
                    .startDate(command.startDate())
                    .endDate(command.endDate())
                    .createdAt(command.createdAt())
                    .build();
        }
    },

    MEET(MeetStatus.MEET_NOT_STARTED) {
        @Override
        public Activity create(ActivityCreateSource command) {
            return Meet.builder()
                    .id(command.id())
                    .study(ActivityLinkedStudy.builder().id(command.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(command.author().id())
                            .name(command.author().name())
                            .image(command.author().image())
                            .build()
                    )
                    .category(command.category())
                    .title(command.title())
                    .content(command.content())
                    .isNotice(command.isNotice())
                    .startDate(command.startDate())
                    .endDate(command.endDate())
                    .createdAt(command.createdAt())
                    .location(command.location())
                    .build();
        }
    },
    ASSIGNMENT(AssignmentStatus.ASSIGNMENT_NOT_STARTED) {
        @Override
        public Activity create(ActivityCreateSource command) {
            return Assignment.builder()
                    .id(command.id())
                    .study(ActivityLinkedStudy.builder().id(command.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(command.author().id())
                            .name(command.author().name())
                            .image(command.author().image())
                            .build()
                    )
                    .category(command.category())
                    .title(command.title())
                    .content(command.content())
                    .isNotice(command.isNotice())
                    .startDate(command.startDate())
                    .endDate(command.endDate())
                    .createdAt(command.createdAt())
                    .build();
        }
    };

    private final ActivityStatus defaultStatus;
    public static ActivityCategory getByName(String category) {
        return Arrays.stream(ActivityCategory.values())
                .filter(c -> c.name().equalsIgnoreCase(category))
                .findAny()
                .orElseThrow(() -> new NotExistsActivityCategoryException(category));
    }
    public abstract Activity create(ActivityCreateSource command);

}
