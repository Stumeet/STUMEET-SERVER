package com.stumeet.server.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsSQSConfig {

    @Value("${spring.cloud.config.server.aws.sqs.region}")
    private String region;

    @Value("${spring.cloud.config.server.aws.sqs.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.config.server.aws.sqs.credentials.secret-key}")
    private String secretKey;

    private AwsBasicCredentials awsBasicCredentials() {
        return AwsBasicCredentials.create(accessKey, secretKey);
    }

    public StaticCredentialsProvider staticCredentialsProvider() {
        return StaticCredentialsProvider.create(awsBasicCredentials());
    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
            .credentialsProvider(staticCredentialsProvider())
            .region(Region.of(region))
            .build();
    }
}
