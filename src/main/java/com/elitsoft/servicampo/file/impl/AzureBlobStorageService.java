package com.elitsoft.servicampo.file.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.time.OffsetDateTime;

/**
 *
 */
@Service
@ConditionalOnProperty(name = "file.storage.platform", havingValue = "azure")
public class AzureBlobStorageService implements ArchivoAlmacenService {

    private final BlobServiceClient blobServiceClient;

    @Autowired
    public AzureBlobStorageService(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    @Override
    public void subirArchivo(String nombreContenedor, String nombreArchivo, byte[] content) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(nombreContenedor);
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);
        blobClient.upload(new ByteArrayInputStream(content), content.length);
    }

    @Override
    public byte[] bajarArchivo(String nombreContenedor, String nombreArchivo) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(nombreContenedor);
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            blobClient.download(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            return null; // Handle exceptions appropriately
        }
    }

    @Override
    public void eliminarArchivo(String nombreContenedor, String nombreArchivo) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(nombreContenedor);
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);
        blobClient.deleteIfExists();
    }

    @Override
    public boolean existeArchivo(String nombreContenedor, String nombreArchivo) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(nombreContenedor);
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);
        return blobClient.exists();
    }

    @Override
    public String generateSignedUrl(String nombreContenedor, String nombreArchivo, int expirationInMinutes) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(nombreContenedor);
        BlobClient blobClient = containerClient.getBlobClient(nombreArchivo);

        BlobSasPermission sasPermission = new BlobSasPermission().setReadPermission(true);

        OffsetDateTime expiryTime = OffsetDateTime.now().plus(Duration.ofMinutes(expirationInMinutes));

        BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, sasPermission);

        return blobClient.generateSas(sasValues);
    }
}