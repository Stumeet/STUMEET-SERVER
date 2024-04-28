package com.stumeet.server.activity.domain.model;

import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import com.stumeet.server.activity.domain.exception.NotExistsActivityStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ActivityCategory {
    DEFAULT {
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

    MEET {
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
    ASSIGNMENT {
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

    public abstract Activity create(ActivityConstructCommand command);

}
