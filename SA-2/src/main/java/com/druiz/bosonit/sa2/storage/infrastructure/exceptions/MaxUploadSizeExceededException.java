package com.druiz.bosonit.sa2.storage.infrastructure.exceptions;

public class MaxUploadSizeExceededException extends StorageException {
    public MaxUploadSizeExceededException(String message) {
        super(message);
    }

    public MaxUploadSizeExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
