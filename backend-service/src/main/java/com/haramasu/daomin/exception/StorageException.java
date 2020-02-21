package com.haramasu.daomin.exception;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/21/2020
 */
public class StorageException extends RuntimeException {

    public StorageException() {
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }

    public StorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
