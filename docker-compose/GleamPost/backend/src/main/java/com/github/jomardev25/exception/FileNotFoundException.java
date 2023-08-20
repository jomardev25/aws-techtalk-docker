package com.github.jomardev25.exception;

public class FileNotFoundException extends FileStorageException {

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
