package com.elitsoft.servicampo.file.impl;

import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 *
 */
@Service
@ConditionalOnProperty(name = "file.storage.platform", havingValue = "local", matchIfMissing = true)
public class LocalArchivoAlmacenService implements ArchivoAlmacenService {

    private final String baseDirectory;

    public LocalArchivoAlmacenService(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public void subirArchivo(String nombreContenedor, String nombreArchivo, byte[] content) {
        Path filePath = Paths.get(baseDirectory, nombreContenedor, nombreArchivo);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, content);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to local filesystem", e);
        }
    }

    @Override
    public byte[] bajarArchivo(String nombreContenedor, String nombreArchivo) {
        Path filePath = Paths.get(baseDirectory, nombreContenedor, nombreArchivo);
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            return null; // File not found or error reading
        }
    }

    @Override
    public void eliminarArchivo(String nombreContenedor, String nombreArchivo) {
        Path filePath = Paths.get(baseDirectory, nombreContenedor, nombreArchivo);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Handle deletion error if needed
        }
    }

    @Override
    public boolean existeArchivo(String nombreContenedor, String nombreArchivo) {
        Path filePath = Paths.get(baseDirectory, nombreContenedor, nombreArchivo);
        return Files.exists(filePath);
    }

    @Override
    public String generateSignedUrl(String nombreContenedor, String nombreArchivo, int expirationInMinutes) {
        // Local file system does not support signed URLs.
        // Returning a path that can be used locally.
        Path filePath = Paths.get(baseDirectory, nombreContenedor, nombreArchivo);
        return filePath.toUri().toString();
    }

    public String moveFile(String bucketName, String objectName, String newObjectName) {
        Path source = Paths.get(baseDirectory, bucketName, objectName);
        Path target = Paths.get(baseDirectory, bucketName, newObjectName);
        try {
            Files.createDirectories(target.getParent());
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error moving file in local filesystem", e);
        }

    }

    public String copyFile(String bucketName, String objectName, String newObjectName) {
        Path source = Paths.get(baseDirectory, bucketName, objectName);
        Path target = Paths.get(baseDirectory, bucketName, newObjectName);
        try {
            Files.createDirectories(target.getParent());
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error copying file in local filesystem", e);
        }
    }

    public String generateTempFileName(String bucketName, String fileExtension) {
        String randomName = UUID.randomUUID().toString();
        return randomName + "." + fileExtension;
    }

}