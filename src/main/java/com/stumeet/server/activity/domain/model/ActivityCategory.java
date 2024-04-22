package com.stumeet.server.activity.domain.model;

import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import com.stumeet.server.activity.domain.exception.NotExistsActivityStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
@Getter
public enum ActivityCategory {
    DEFAULT(command ->
            Default.builder()
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
                    .build()
    ),

    MEET(command ->
            Meet.builder()
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
                    .build()
    ),
    ASSIGNMENT(command ->
            Assignment.builder()
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
                    .build()
    );

    private final Function<ActivityConstructCommand, Activity> factory;

    public static Activity createByCategory(ActivityCategory category, ActivityConstructCommand command) {
        return Arrays.stream(values())
                .filter(c -> c.equals(category))
                .findFirst()
                .map(c -> c.factory.apply(command))
                .orElseThrow(() -> new NotExistsActivityStatusException(category.name()));
    }
}
