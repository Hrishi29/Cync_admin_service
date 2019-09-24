package com.idexcel.adminservice.exception;

public class LenderExistsException extends RuntimeException {
    public LenderExistsException(String message) {
        super(message);
    }

    public LenderExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LenderExistsException(Throwable cause) {
        super(cause);
    }


}
