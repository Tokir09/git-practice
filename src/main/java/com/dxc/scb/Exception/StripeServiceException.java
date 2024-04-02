package com.dxc.scb.Exception;

public class StripeServiceException extends RuntimeException {

    public StripeServiceException(String message) {
        super(message);
    }

    public StripeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
