package com.elitsoft.servicampo.file.directory;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
public class ContenedorDirectorio {

    @Value("${file.storage.buckets.main}")
    private String mainBucket;

}