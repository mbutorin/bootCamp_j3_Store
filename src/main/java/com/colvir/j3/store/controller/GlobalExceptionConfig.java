package com.colvir.j3.store.controller;

import com.colvir.j3.store.exception.NotImplementedException;
import com.colvir.j3.store.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(final @NonNull UserNotFoundException e) {
        return createResponse(HttpStatus.PRECONDITION_FAILED, e);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<String> handleNotImplementedException(final @NonNull NotImplementedException e) {
        return createResponse(HttpStatus.PRECONDITION_FAILED, e);
    }

    private ResponseEntity<String> createResponse(@NonNull final HttpStatus status, @NonNull final Exception e) {
        log.error("Exception happened. Message is: " + e.toString());
        return ResponseEntity.status(status)
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
    }
}
