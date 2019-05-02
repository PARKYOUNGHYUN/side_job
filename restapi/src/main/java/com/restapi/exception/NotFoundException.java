package com.restapi.exception;

/**
 * 操作しようとしたリソースが存在しない場合にthrowされるException
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
