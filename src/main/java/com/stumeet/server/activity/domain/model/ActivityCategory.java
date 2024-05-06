package com.stumeet.server.activity.domain.model;

import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActivityCategory {
    DEFAULT(DefaultStatus.NONE) {
        @Override
        public Activity create(ActivityConstructCommand command) {
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
        public Activity create(ActivityConstructCommand command) {
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
        public Activity create(ActivityConstructCommand command) {
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

    public abstract Activity create(ActivityConstructCommand command);

}
