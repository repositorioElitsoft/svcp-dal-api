package com.elitsoft.servicampo.file.impl;

import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
@ConditionalOnProperty(name = "file.storage.platform", havingValue = "gcp")
public class GcpArchivoAlmacenService implements ArchivoAlmacenService {

    private final Storage storage;

    @Autowired
    public GcpArchivoAlmacenService(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void subirArchivo(String nombreContenedor, String nombreArchivo, byte[] content) {
        BlobId blobId = BlobId.of(nombreContenedor, nombreArchivo);
        Blob blob = storage.create(BlobInfo.newBuilder(blobId).build(), content);
    }

    @Override
    public byte[] bajarArchivo(String nombreContenedor, String nombreArchivo) {
        BlobId blobId = BlobId.of(nombreContenedor, nombreArchivo);
        Blob blob = storage.get(blobId);
        if (blob != null) {
            return blob.getContent();
        }
        return null;
    }

    @Override
    public void eliminarArchivo(String nombreContenedor, String nombreArchivo) {
        BlobId blobId = BlobId.of(nombreContenedor, nombreArchivo);
        storage.delete(blobId);
    }

    @Override
    public boolean existeArchivo(String nombreContenedor, String nombreArchivo) {
        BlobId blobId = BlobId.of(nombreContenedor, nombreArchivo);
        Blob blob = storage.get(blobId);
        return blob != null && blob.exists();
    }

    @Override
    public String generateSignedUrl(String nombreContenedor, String nombreArchivo, int expirationInMinutes) {
        BlobId blobId = BlobId.of(nombreContenedor, nombreArchivo);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        URL url = storage.signUrl(
                blobInfo,
                expirationInMinutes,
                TimeUnit.MINUTES,
                Storage.SignUrlOption.httpMethod(HttpMethod.GET),
                Storage.SignUrlOption.withV4Signature());

        return url.toString();
    }
}