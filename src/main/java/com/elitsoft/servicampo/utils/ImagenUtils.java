package com.elitsoft.servicampo.utils;

import org.springframework.http.MediaType;

/**
 * Utility class for image-related operations
 */
public class ImagenUtils {

    /**
     * Determines the media type based on the image data's magic numbers
     *
     * @param imageData The image byte array
     * @return The appropriate MediaType
     */
    public static MediaType determinarTipoFormato(byte[] imageData) {
        if (imageData == null || imageData.length < 8) {
            return MediaType.IMAGE_JPEG; // Default to JPEG if data is insufficient
        }

        // Check for PNG signature (89 50 4E 47 0D 0A 1A 0A)
        if (imageData[0] == (byte) 0x89 &&
                imageData[1] == (byte) 0x50 &&
                imageData[2] == (byte) 0x4E &&
                imageData[3] == (byte) 0x47 &&
                imageData[4] == (byte) 0x0D &&
                imageData[5] == (byte) 0x0A &&
                imageData[6] == (byte) 0x1A &&
                imageData[7] == (byte) 0x0A) {
            return MediaType.IMAGE_PNG;
        }

        // Check for JPEG signature (FF D8 FF)
        if (imageData[0] == (byte) 0xFF &&
                imageData[1] == (byte) 0xD8 &&
                imageData[2] == (byte) 0xFF) {
            return MediaType.IMAGE_JPEG;
        }

        // Check for GIF signature (47 49 46 38)
        if (imageData[0] == (byte) 0x47 &&
                imageData[1] == (byte) 0x49 &&
                imageData[2] == (byte) 0x46 &&
                imageData[3] == (byte) 0x38) {
            return MediaType.valueOf("image/gif");
        }

        // Default to JPEG if no signature matches
        return MediaType.IMAGE_JPEG;
    }

    /**
     * Returns the file extension based on media type
     *
     * @param mediaType The media type
     * @return The appropriate file extension including the dot
     */
    public static String extensionArchivo(MediaType mediaType) {
        if (mediaType.equals(MediaType.IMAGE_PNG)) {
            return ".png";
        } else if (mediaType.equals(MediaType.IMAGE_JPEG)) {
            return ".jpeg";
        } else if (mediaType.equals(MediaType.valueOf("image/gif"))) {
            return ".gif";
        }

        return ".jpeg"; // Default
    }
} 