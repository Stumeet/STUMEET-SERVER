package com.stumeet.server.batch.job.notification;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.stumeet.server.batch.adapter.persistence.repository.StudyMemberRepository;
import com.stumeet.server.batch.domain.study.StudyMember;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GrapeStatusResetJobConfig {

    private final StudyMemberRepository studyMemberRepository;

    @Bean(name = "grapeStatusResetItemReader")
    @StepScope
    public RepositoryItemReader<StudyMember> itemReader(StudyMemberRepository repository) {
        String today = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return new RepositoryItemReaderBuilder<StudyMember>()
                .name("startDateStudyItemReader")
                .repository(repository)
                .methodName("findStudyMembersByStudyStartDay")
                .arguments(today)
                .pageSize(100)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

    @Bean(name = "grapeStatusResetItemProcessor")
    public ItemProcessor<StudyMember, StudyMember> itemProcessor() {
        return studyMember -> {
            studyMember.resetGrape();
            return studyMember;
        };
    }

    @Bean(name = "grapeStatusResetItemWriter")
    public ItemWriter<StudyMember> itemWriter(StudyMemberRepository studyMemberRepository) {
        return studyMemberRepository::saveAll;
    }

    @Bean(name = "grapeStatusResetJob")
    public Job job(JobRepository jobRepository, JpaTransactionManager transactionManager) {
        return new JobBuilder("GrapeStatusReset", jobRepository)
                .start(new StepBuilder("ResetStudyMemberGrapeStatus", jobRepository)
                        .<StudyMember, StudyMember>chunk(100, transactionManager)
                        .reader(itemReader(studyMemberRepository))
                        .processor(itemProcessor())
                        .writer(itemWriter(studyMemberRepository))
                        .build())
                .build();
    }
}
