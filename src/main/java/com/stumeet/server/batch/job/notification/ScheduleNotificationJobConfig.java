package com.stumeet.server.batch.job.notification;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.stumeet.server.batch.domain.member.Member;
import com.stumeet.server.batch.domain.member.internal.MemberScheduleNotificationProcessor;
import com.stumeet.server.batch.dto.Notification;
import com.stumeet.server.batch.repository.ActivityRepository;
import com.stumeet.server.batch.repository.MemberRepository;
import com.stumeet.server.notification.adapter.out.persistence.NotificationLogRepository;
import com.stumeet.server.notification.adapter.out.persistence.entity.NotificationLogJpaEntity;
import com.stumeet.server.notification.application.port.out.NotificationSendPort;
import com.stumeet.server.notification.application.port.out.command.SendMessageCommand;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ScheduleNotificationJobConfig {

    @Bean
    @StepScope
    public RepositoryItemReader<Member> itemReader(MemberRepository repository) {
        return new RepositoryItemReaderBuilder<Member>()
            .name("todayActivityParticipantItemReader")
            .repository(repository)
            .methodName("findTodayActivityParticipants")
            .arguments(LocalDate.now())
            .pageSize(100)
            .sorts(Map.of("id", Sort.Direction.ASC))
            .build();
    }

    @Bean
    public ItemWriter<Notification> itemWriter(NotificationLogRepository notificationLogRepository,
        NotificationSendPort notificationSendPort) {
        return chunk -> {
            for (Notification notification : chunk.getItems()) {
                SendMessageCommand command = new SendMessageCommand(
                    notification.tokens(),
                    notification.topic(),
                    notification.title(),
                    notification.body(),
                    notification.imgUrl(),
                    notification.data()
                );
                notificationSendPort.sendTokenMulticastMessage(command);

                NotificationLogJpaEntity notificationLog = NotificationLogJpaEntity.builder()
                    .memberId(notification.memberId())
                    .title(notification.title())
                    .body(notification.body())
                    .imgUrl(notification.imgUrl())
                    .data(notification.data().toString())
                    .build();
                notificationLogRepository.save(notificationLog);
            }
        };
    }

    @Bean
    public Job job(JobRepository jobRepository, JpaTransactionManager transactionManager, RepositoryItemReader<Member> itemReader,
        ItemWriter<Notification> itemWriter, ActivityRepository activityRepository) {
        return new JobBuilder("memberScheduleNotification", jobRepository)
            .start(new StepBuilder("makeMemberScheduleNotification", jobRepository)
                .<Member, Notification>chunk(100, transactionManager)
                .reader(itemReader)
                .processor(new MemberScheduleNotificationProcessor(activityRepository))
                .writer(itemWriter)
                .build())
            .build();
    }
}
