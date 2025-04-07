package com.elitsoft.servicampo.file.impl;

import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

/**
 *
 */
@Service
@ConditionalOnProperty(name = "file.storage.platform", havingValue = "aws")
public class AwsS3ArchivoAlmacenService implements ArchivoAlmacenService {


    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Autowired
    public AwsS3ArchivoAlmacenService(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    @Override
    public void subirArchivo(String nombreContenedor, String nombreArchivo, byte[] content) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(nombreContenedor)
                .key(nombreArchivo)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));
    }

    @Override
    public byte[] bajarArchivo(String nombreContenedor, String nombreArchivo) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(nombreContenedor)
                .key(nombreArchivo)
                .build();
        try {
            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
            return objectBytes.asByteArray();
        } catch (NoSuchKeyException e) {
            return null; // File not found
        }
    }

    @Override
    public void eliminarArchivo(String nombreContenedor, String nombreArchivo) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(nombreContenedor)
                .key(nombreArchivo)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public boolean existeArchivo(String nombreContenedor, String nombreArchivo) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(nombreContenedor)
                    .key(nombreArchivo)
                    .build();
            s3Client.headObject(headObjectRequest);
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        }
    }

    @Override
    public String generateSignedUrl(String nombreContenedor, String nombreArchivo, int expirationInMinutes) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(nombreContenedor)
                .key(nombreArchivo)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(Duration.ofMinutes(expirationInMinutes))
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(presignRequest);

        return presignedGetObjectRequest.url().toString();
    }
}