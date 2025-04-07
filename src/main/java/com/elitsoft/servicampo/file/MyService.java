package com.elitsoft.servicampo.file;

import com.elitsoft.servicampo.file.directory.FolderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private final ArchivoAlmacenService archivoAlmacenService;

    @Autowired
    private final FolderConfig folderConfig;

    @Value("${file.storage.buckets.main}")
    private String mainBucket;

    @Autowired
    public MyService(ArchivoAlmacenService archivoAlmacenService, FolderConfig folderConfig) {
        this.archivoAlmacenService = archivoAlmacenService;
        this.folderConfig = folderConfig;
    }

    public void uploadImage(byte[] content, String imageName) {
        String objectKey = folderConfig.getImagenFolder() + "/" + imageName;
        archivoAlmacenService.subirArchivo(mainBucket, objectKey, content);
    }

    public void uploadEvidence(byte[] content, String evidenceName) {
//        String objectKey = folderConfig.getEvidencesFolder() + "/" + evidenceName;
//        archivoAlmacenService.uploadFile(mainBucket, objectKey, content);
    }

    public void uploadObservation(byte[] content, String observationName){
//        String objectKey = folderConfig.getObservationsFolder() + "/" + observationName;
//        archivoAlmacenService.uploadFile(mainBucket, objectKey, content);
    }

    public byte[] downloadImage(String imageName) {
        String objectKey = folderConfig.getImagenFolder() + "/" + imageName;
        return archivoAlmacenService.bajarArchivo(mainBucket, objectKey);
    }

}