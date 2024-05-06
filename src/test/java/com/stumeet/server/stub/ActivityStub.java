package com.stumeet.server.stub;

import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.domain.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityStub {
    private ActivityStub() {}

    public static String getInvalidCreateActivityJson() {
        return "{\"category\":\"DEFAULT\",\"title\":\"\",\"content\":\"\",\"images\":[\"https://example.com/image1.png\", \"https://example.com/image2.png\", \"https://example.com/image3.png\", \"https://example.com/image4.png\", \"https://example.com/image5.png\", \"https://example.com/image6.png\"],\"isNotice\":false,\"startDate\":\"2024-04-01T00:00:00\",\"endDate\":\"2050-05-01T00:00:00\",\"location\":null,\"participants\":[]}";
    }
    public static ActivityCreateCommand getDefaultActivityCreateCommand() {
        return ActivityCreateCommand.builder()
                .category(ActivityCategory.DEFAULT)
                .title("title")
                .content("content")
                .images(List.of("https://example.com/image1.png", "https://example.com/image2.png"))
                .isNotice(false)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .participants(List.of(MemberStub.getMemberId()))
                .build();
    }

    public static ActivityConstructCommand getDefaultConstructCommand() {
        return ActivityConstructCommand.builder()
                .id(null)
                .studyId(StudyStub.getStudyId())
                .author(ActivityConstructCommand.ActivityMemberConstructCommand.builder()
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

}
