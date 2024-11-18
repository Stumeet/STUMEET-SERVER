package com.stumeet.server.batch.domain.member.internal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;

import com.stumeet.server.batch.domain.member.Device;
import com.stumeet.server.batch.domain.member.Member;
import com.stumeet.server.batch.domain.study.Activity;
import com.stumeet.server.batch.dto.Notification;
import com.stumeet.server.batch.repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberScheduleNotificationProcessor implements ItemProcessor<Member, Notification> {
    private static final String TITLE = "오늘의 스터디 일정을 확인해보세요!";
    private static final String FORMAT = "[%s] %s %s";

    private final ActivityRepository activityRepository;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public Notification process(Member item) {
        LocalDate today = LocalDate.now();
        List<Activity> activities = activityRepository.findAllMemberTodayActivities(item.getId(), today);

        return new Notification(
            item.getId(),
            TITLE,
            makeBody(activities),
            null,
            Map.of(),
            null,
            item.getDevices().stream().map(Device::getNotificationToken).toList()
        );
    }

    private String makeBody(List<Activity> activities) {
        List<String> lines = new ArrayList<>();

        for (Activity activity : activities) {
            LocalDateTime dateTime = switch (activity.getCategory()) {
                case MEET -> activity.getStartDate();
                case ASSIGNMENT -> activity.getEndDate();
                case DEFAULT -> null;
            };

            if (dateTime == null) {
                continue;
            }

            lines.add(
                String.format(FORMAT,
                    activity.getStudy().getName(),
                    dateTime.format(timeFormatter),
                    activity.getTitle()));
        }

        return String.join("\n", lines);
    }
}