package com.cmani.woven.filestorage.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends RuntimeException{

    private String message;
    private HttpStatus status;
    private int errorCode;

    public FileStorageException() {
    }

    public FileStorageException(String message, HttpStatus status, int errorCode) {
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
