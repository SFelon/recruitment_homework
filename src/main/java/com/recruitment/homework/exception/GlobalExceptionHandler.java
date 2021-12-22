package com.recruitment.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanAppRuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleAppRuntimeException(LoanAppRuntimeException ex) {
        final ExceptionResponse response = prepareResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintException(ConstraintViolationException ex) {
        final List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + "[" +
                    violation.getPropertyPath() + "]: " + violation.getMessage());
        }
        final ExceptionResponse response = prepareResponse(String.join(", ", errors));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        final List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add("[" + error.getField() + "]: " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add("[" + error.getObjectName() + "]: " + error.getDefaultMessage());
        }
        final ExceptionResponse response = prepareResponse(String.join(", ", errors));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(EntityNotFoundException ex) {
        final ExceptionResponse response = prepareResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedException(UnsupportedOperationException ex) {
        final ExceptionResponse response = prepareResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    private ExceptionResponse prepareResponse(String message) {
        return new ExceptionResponse(message, UUID.randomUUID().toString());
    }

}
