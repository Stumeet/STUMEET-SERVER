package com.stumeet.server.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@Profile("!test")
public class AwsS3Config {

    @Value("${spring.cloud.config.server.aws.s3.region}")
    private String region;

    @Value("${spring.cloud.config.server.aws.s3.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.config.server.aws.s3.credentials.secret-key}")
    private String secretKey;

    private AwsBasicCredentials awsBasicCredentials() {
        return AwsBasicCredentials.create(accessKey, secretKey);
    }

    public StaticCredentialsProvider staticCredentialsProvider() {
        return StaticCredentialsProvider.create(awsBasicCredentials());
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(staticCredentialsProvider())
                .region(Region.of(region))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .credentialsProvider(staticCredentialsProvider())
                .region(Region.of(region))
                .build();
    }
}
