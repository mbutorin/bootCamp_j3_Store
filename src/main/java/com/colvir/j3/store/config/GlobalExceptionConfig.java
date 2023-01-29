package com.colvir.j3.store.config;

import com.colvir.j3.store.exception.NotImplementedException;
import com.colvir.j3.store.exception.RecordBadData;
import com.colvir.j3.store.exception.RecordNotFoundException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(final @NonNull Exception e) throws Exception {
        if (e instanceof UserNotFoundException) {
            return createResponse(HttpStatus.NOT_FOUND, e);
        } else if (e instanceof NotImplementedException) {
            return createResponse(HttpStatus.NOT_IMPLEMENTED, e);
        } else if (e instanceof RecordBadData) {
            return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, e);
        } else if (e instanceof RecordNotFoundException) {
            return createResponse(HttpStatus.NOT_FOUND, e);
        }
        throw e;
    }

    private ResponseEntity<String> createResponse(@NonNull final HttpStatus status, @NonNull final Exception e) {
        log.error("Exception happened. Message is: " + e.toString());
        return ResponseEntity.status(status)
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
    }
}
