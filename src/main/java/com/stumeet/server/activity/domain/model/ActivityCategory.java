package com.stumeet.server.activity.domain.model;

import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.exception.InvalidCategoryStatusException;
import com.stumeet.server.activity.domain.exception.NotExistsActivityCategoryException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ActivityCategory {
    DEFAULT(DefaultStatus.NONE) {
        @Override
        public Activity create(ActivitySource source) {
            return Default.builder()
                    .id(source.id())
                    .study(ActivityLinkedStudy.builder().id(source.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(source.author().id())
                            .name(source.author().name())
                            .image(source.author().image())
                            .build()
                    )
                    .category(source.category())
                    .title(source.title())
                    .content(source.content())
                    .link(source.link())
                    .isNotice(source.isNotice())
                    .createdAt(source.createdAt())
                    .build();
        }

        @Override
        public void validateStatus(ActivityStatus status) {
            boolean isNotDefaultStatus = Arrays.stream(DefaultStatus.values())
                    .noneMatch(defaultStatus -> defaultStatus.equals(status));

            if (isNotDefaultStatus) {
                throw new InvalidCategoryStatusException();
            }
        }
    },

    MEET(MeetStatus.MEET_NOT_STARTED) {
        @Override
        public Activity create(ActivitySource source) {
            return Meet.builder()
                    .id(source.id())
                    .study(ActivityLinkedStudy.builder().id(source.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(source.author().id())
                            .name(source.author().name())
                            .image(source.author().image())
                            .build()
                    )
                    .category(source.category())
                    .title(source.title())
                    .content(source.content())
                    .location(source.location())
                    .link(source.link())
                    .startDate(source.startDate())
                    .endDate(source.endDate())
                    .isNotice(source.isNotice())
                    .createdAt(source.createdAt())
                    .build();
        }

        @Override
        public void validateStatus(ActivityStatus status) {
            boolean isNotMeetStatus = Arrays.stream(MeetStatus.values())
                    .noneMatch(meetStatus -> meetStatus.equals(status));

            if (isNotMeetStatus) {
                throw new InvalidCategoryStatusException();
            }
        }
    },
    ASSIGNMENT(AssignmentStatus.ASSIGNMENT_NOT_STARTED) {
        @Override
        public Activity create(ActivitySource source) {
            return Assignment.builder()
                    .id(source.id())
                    .study(ActivityLinkedStudy.builder().id(source.studyId()).build())
                    .author(ActivityMember.builder()
                            .id(source.author().id())
                            .name(source.author().name())
                            .image(source.author().image())
                            .build()
                    )
                    .category(source.category())
                    .title(source.title())
                    .content(source.content())
                    .link(source.link())
                    .startDate(source.startDate())
                    .endDate(source.endDate())
                    .isNotice(source.isNotice())
                    .createdAt(source.createdAt())
                    .build();
        }

        @Override
        public void validateStatus(ActivityStatus status) {
            boolean isNotAssignmentStatus = Arrays.stream(AssignmentStatus.values())
                    .noneMatch(assignmentStatus -> assignmentStatus.equals(status));

            if (isNotAssignmentStatus) {
                throw new InvalidCategoryStatusException();
            }
        }
    };

    private final ActivityStatus defaultStatus;

    public static ActivityCategory getByName(String category) {
        return Arrays.stream(ActivityCategory.values())
                .filter(c -> c.name().equalsIgnoreCase(category))
                .findAny()
                .orElseThrow(() -> new NotExistsActivityCategoryException(category));
    }

    public abstract Activity create(ActivitySource source);

    public abstract void validateStatus(ActivityStatus status);
}
