package com.idexcel.adminservice.exception;

public class LenderNotFoundException extends RuntimeException {


    public LenderNotFoundException(String message) {
        super(message);
    }

    public LenderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LenderNotFoundException(Throwable cause) {
        super(cause);
    }
}
