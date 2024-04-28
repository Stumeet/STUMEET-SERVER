package com.stumeet.server.helper;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@TestConfiguration
@Testcontainers
public class TestAwsS3Config {
    protected static final DockerImageName LOCALSTACK_CONTAINER_VERSION = DockerImageName.parse("localstack/localstack:3.2");

    private static final LocalStackContainer LOCALSTACK_CONTAINER = new LocalStackContainer(LOCALSTACK_CONTAINER_VERSION)
            .withServices(LocalStackContainer.Service.S3);

    static {
        LOCALSTACK_CONTAINER.start();
    }

    @Bean
    public S3Client s3Client() {
        S3Client s3Client = S3Client.builder()
                .endpointOverride(LOCALSTACK_CONTAINER.getEndpoint())
                .region(Region.of(LOCALSTACK_CONTAINER.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(LOCALSTACK_CONTAINER.getAccessKey(), LOCALSTACK_CONTAINER.getSecretKey())))
                .build();
        s3Client.createBucket(b -> b.bucket("stumeet"));
        return s3Client;
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .endpointOverride(LOCALSTACK_CONTAINER.getEndpoint())
                .region(Region.of(LOCALSTACK_CONTAINER.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(LOCALSTACK_CONTAINER.getAccessKey(), LOCALSTACK_CONTAINER.getSecretKey())))
                .build();
    }


}
