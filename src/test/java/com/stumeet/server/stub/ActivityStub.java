package com.stumeet.server.stub;

import com.stumeet.server.activity.adapter.in.response.*;
import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.domain.model.*;
import com.stumeet.server.member.domain.Member;

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

    public static ActivityCreateSource getDefaultCreateSource() {
        Member member = MemberStub.getMember();
        return ActivityCreateSource.builder()
                .id(1L)
                .studyId(StudyStub.getStudyId())
                .author(ActivityCreateSource.ActivityMemberCreateSource.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .image(member.getImage())
                        .build())
                .category(ActivityCategory.DEFAULT)
                .title("title")
                .content("content")
                .isNotice(true)
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Activity getDefaultActivity() {
        return ActivityCategory.DEFAULT.create(getDefaultCreateSource());
    }

    public static List<ActivityImage> getActivityImages(Activity activity) {
        return List.of(
                ActivityImage.builder()
                        .id(1L)
                        .activity(activity)
                        .url("https://example.com/image1.png")
                        .build(),
                ActivityImage.builder()
                        .id(2L)
                        .activity(activity)
                        .url("https://example.com/image2.png")
                        .build(),
                ActivityImage.builder()
                        .id(3L)
                        .activity(activity)
                        .url("https://example.com/image2.png")
                        .build()
        );
    }

    public static List<ActivityImageResponse> getActivityImageResponses() {
        return getActivityImages(ActivityStub.getDefaultActivity()).stream()
                .map(image -> ActivityImageResponse.builder()
                        .id(image.getId())
                        .imageUrl(image.getUrl())
                        .build())
                .toList();
    }

    public static List<ActivityParticipant> getActivityParticipants(Activity activity) {
        return List.of(
                ActivityParticipant.builder()
                        .id(1L)
                        .activity(activity)
                        .member(getAuthor())
                        .status(activity.getCategory().getDefaultStatus())
                        .build(),
                ActivityParticipant.builder()
                        .id(2L)
                        .activity(activity)
                        .member(getParticipant())
                        .status(activity.getCategory().getDefaultStatus())
                        .build()
        );
    }

    public static ActivityParticipantStatusResponses getActivityParticipantStatusResponses() {
        return ActivityParticipantStatusResponses.builder()
                .participants(List.of(
                        ActivityParticipantStatusResponse.builder()
                                .id(getAuthor().getId())
                                .name(getAuthor().getName())
                                .profileImageUrl(getAuthor().getImage())
                                .status(DefaultStatus.NONE.getDescription())
                                .build(),
                        ActivityParticipantStatusResponse.builder()
                                .id(getParticipant().getId())
                                .name(getParticipant().getName())
                                .profileImageUrl(getParticipant().getImage())
                                .status(DefaultStatus.NONE.getDescription())
                                .build()
                ))
                .build();
    }

    public static ActivityMember getParticipant() {
        Member member = MemberStub.getStudyJoinMember();
        return ActivityMember.builder()
                .id(MemberStub.getStudyMemberId())
                .name(member.getName())
                .image(member.getImage())
                .build();
    }

    public static ActivityMember getAuthor() {
        Member member = MemberStub.getMember();
        return ActivityMember.builder()
                .id(member.getId())
                .name(member.getName())
                .image(member.getImage())
                .build();
    }

    public static ActivityParticipantSimpleResponse getAuthorResponse() {
        ActivityMember author = getAuthor();

        return ActivityParticipantSimpleResponse.builder()
                .memberId(author.getId())
                .name(author.getName())
                .profileImageUrl(author.getImage())
                .build();
    }

    public static ActivityParticipantSimpleResponse getParticipantResponse() {
        ActivityMember participant = getParticipant();

        return ActivityParticipantSimpleResponse.builder()
                .memberId(participant.getId())
                .name(participant.getName())
                .profileImageUrl(participant.getImage())
                .build();
    }

    public static Long getActivityId() {
        return 1L;
    }

    public static Long getInvalidActivityId() {
        return 0L;
    }

    public static ActivityDetailResponse getActivityDetailResponse() {
        return ActivityDetailResponse.builder()
                .id(1L)
                .title("title")
                .content("content")
                .author(getAuthorResponse())
                .imageUrl(getActivityImageResponses())
                .participants(List.of(getAuthorResponse(), getParticipantResponse()))
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .status(DefaultStatus.NONE.getDescription())
                .build();
    }
    public static ActivityDetailResponse getActivityDetailResponseForNotJoinedUser() {
        return ActivityDetailResponse.builder()
                .id(1L)
                .title("title")
                .content("content")
                .author(getAuthorResponse())
                .imageUrl(getActivityImageResponses())
                .participants(List.of(getAuthorResponse(), getParticipantResponse()))
                .startDate(LocalDateTime.parse("2024-04-01T00:00:00"))
                .endDate(LocalDateTime.parse("2050-05-01T00:00:00"))
                .status(NotJoinedStatus.NOT_JOINED.getDescription())
                .build();
    }
}
