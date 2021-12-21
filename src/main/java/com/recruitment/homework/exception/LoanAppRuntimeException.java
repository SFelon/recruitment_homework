package com.recruitment.homework.exception;

public class LoanAppRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1522103775201087195L;

    public LoanAppRuntimeException() {
    }

    public LoanAppRuntimeException(String message) {
        super(message);
    }

    public LoanAppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
