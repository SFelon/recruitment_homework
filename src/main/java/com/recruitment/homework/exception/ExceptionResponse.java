package com.recruitment.homework.exception;


import java.io.Serializable;
import java.util.Objects;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 9126460909960558881L;

    private String message;
    private String exceptionId;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String exceptionId) {
        this.message = message;
        this.exceptionId = exceptionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return exceptionId.equals(that.exceptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionId);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", exceptionId='" + exceptionId + '\'' +
                '}';
    }
}
