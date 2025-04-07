package com.elitsoft.servicampo.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import com.elitsoft.servicampo.file.impl.AwsS3ArchivoAlmacenService;
import com.elitsoft.servicampo.file.impl.AzureBlobStorageService;
import com.elitsoft.servicampo.file.impl.GcpArchivoAlmacenService;
import com.elitsoft.servicampo.file.impl.LocalArchivoAlmacenService;
import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.spring.core.GcpProjectIdProvider;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;

/**
 *
 */
@Configuration
public class ArchivoAlmacenConfig {

    @Value("${file.storage.platform}")
    private String platform;

    @Bean
    @ConditionalOnProperty(name = "file.storage.platform", havingValue = "gcp")
    @ConditionalOnBean({CredentialsProvider.class, GcpProjectIdProvider.class})
    public Storage gcpStorage(
            GcpProjectIdProvider projectIdProvider,
            CredentialsProvider credentialsProvider
    ) throws IOException {
        return StorageOptions.newBuilder()
                .setProjectId(projectIdProvider.getProjectId())
                .setCredentials(credentialsProvider.getCredentials())  // Use existing credentials
                .build()
                .getService();
    }


    @Bean
    @ConditionalOnProperty(name = "file.storage.platform", havingValue = "aws")
    public S3Client s3Client() {
        return S3Client.builder().build();
    }

    @Bean
    @ConditionalOnProperty(name = "file.storage.platform", havingValue = "aws")
    public S3Presigner s3Presigner() {
        return S3Presigner.builder().build();
    }

    @Bean
    @ConditionalOnProperty(name = "file.storage.platform", havingValue = "azure")
    public BlobServiceClient azureClient() {
        return new BlobServiceClientBuilder().buildClient();
    }

    @Bean
    public ArchivoAlmacenService archivoAlmacenService(
            java.util.Optional<Storage> gcpStorage,
            java.util.Optional<S3Client> s3Client,
            java.util.Optional<S3Presigner> s3Presigner,
            java.util.Optional<BlobServiceClient> azureClient) {
        if ("gcp".equalsIgnoreCase(platform) && gcpStorage.isPresent()) {
            return new GcpArchivoAlmacenService(gcpStorage.get());
        } else if ("aws".equalsIgnoreCase(platform) && s3Client.isPresent() && s3Presigner.isPresent()) {
            return new AwsS3ArchivoAlmacenService(s3Client.get(), s3Presigner.get());
        } else if ("azure".equalsIgnoreCase(platform) && azureClient.isPresent()) {
            return new AzureBlobStorageService(azureClient.get());
        } else if ("local".equalsIgnoreCase(platform)) {
            return new LocalArchivoAlmacenService("/path/to/local/storage");
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }
}