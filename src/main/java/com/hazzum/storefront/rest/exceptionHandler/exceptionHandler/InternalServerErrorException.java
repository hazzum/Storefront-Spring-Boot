package com.hazzum.storefront.rest.exceptionHandler.exceptionHandler;

@SuppressWarnings("serial")
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}
