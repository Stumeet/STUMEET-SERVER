package com.stumeet.server.batch.job.notification;

import java.time.LocalDate;
import java.util.List;
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

import com.stumeet.server.batch.adapter.persistence.mapper.NotificationLogPersistenceMapper;
import com.stumeet.server.batch.adapter.persistence.repository.NotificationLogRepository;
import com.stumeet.server.batch.domain.member.Member;
import com.stumeet.server.batch.domain.member.internal.MemberScheduleNotificationProcessor;
import com.stumeet.server.batch.dto.Notification;
import com.stumeet.server.batch.adapter.persistence.repository.ActivityRepository;
import com.stumeet.server.batch.adapter.persistence.repository.MemberRepository;
import com.stumeet.server.notification.application.port.out.NotificationQueuePort;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ScheduleNotificationJobConfig {

    @Bean(name = "memberScheduleNotificationItemReader")
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

    @Bean(name = "memberScheduleNotificationItemWriter")
    public ItemWriter<Notification> itemWriter(NotificationLogRepository notificationLogRepository,
        NotificationQueuePort notificationQueuePort, NotificationLogPersistenceMapper mapper) {
        return chunk -> {
            List<Notification> notifications = chunk.getItems().stream()
                .map(Notification.class::cast)
                .toList();

            notificationQueuePort.publishNotifications(notifications);
            notificationLogRepository.saveAll(mapper.toDomains(notifications));
        };
    }

    @Bean(name = "memberScheduleNotificationJob")
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
