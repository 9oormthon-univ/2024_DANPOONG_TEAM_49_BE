package com.goormthon3.team49.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;  // import 추가
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AmazonS3Config {

    @Value("${cloud.aws.credentials.access-key}") // application.yml에서 설정한 AWS 액세스 키
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}") // application.yml에서 설정한 AWS 비밀 키
    private String secretKey;

    @Value("${cloud.aws.region.static}") // application.yml에서 설정한 AWS 리전
    private String region;

    @Value("${cloud.aws.s3.bucket}") // application.yml에서 설정한 S3 버킷 이름
    private String bucket;

    /**
     * AmazonS3Client 빈을 설정합니다.
     * @return AmazonS3 클라이언트 인스턴스
     */
    @Bean
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
