package com.stumeet.server.stub;

import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.domain.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityStub {
    private ActivityStub() {}

    public static ActivityCreateCommand getInvalidCreateActivity() {
        return ActivityCreateCommand.builder()
                .category("DEFAULT")
                .title("")
                .content("")
                .images(List.of("https://example.com/image1.png", "https://example.com/image2.png"))
                .isNotice(false)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .participants(List.of())
                .build();
    }

    public static ActivityCreateCommand getInvalidCategoryCreateActivity() {
        return ActivityCreateCommand.builder()
                .category("INVALID")
                .title("title")
                .content("content")
                .images(List.of("https://example.com/image1.png", "https://example.com/image2.png"))
                .isNotice(false)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .participants(List.of(MemberStub.getMemberId()))
                .build();
    }

    public static ActivityCreateCommand getDefaultActivityCreateCommand() {
        return ActivityCreateCommand.builder()
                .category("DEFAULT")
                .title("title")
                .content("content")
                .images(List.of("https://example.com/image1.png", "https://example.com/image2.png"))
                .isNotice(false)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .participants(List.of(MemberStub.getMemberId()))
                .build();
    }

    public static ActivityCreateSource getDefaultConstructCommand() {
        return ActivityCreateSource.builder()
                .id(null)
                .studyId(StudyStub.getStudyId())
                .author(ActivityCreateSource.ActivityMemberCreateSource.builder()
                        .id(MemberStub.getMemberId())
                        .build())
                .category(ActivityCategory.DEFAULT)
                .title("title")
                .content("content")
                .isNotice(false)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Activity getDefaultActivity() {
        return ActivityCategory.DEFAULT.create(getDefaultConstructCommand());
    }

    public static List<ActivityImage> getActivityImages(Activity activity) {
        return List.of(
                ActivityImage.builder()
                        .activity(activity)
                        .url("https://example.com/image1.png")
                        .build(),
                ActivityImage.builder()
                        .activity(activity)
                        .url("https://example.com/image2.png")
                        .build()
        );
    }

    public static List<ActivityParticipant> getActivityParticipants(Activity activity) {
        return List.of(
                ActivityParticipant.builder()
                        .activity(activity)
                        .member(ActivityMember.builder().id(MemberStub.getMemberId()).build())
                        .status(activity.getCategory().getDefaultStatus())
                        .build()
        );
    }

    public static Long getActivityId() {
        return 1L;
    }

    public static Long getInvalidActivityId() {
        return 0L;
    }
}
