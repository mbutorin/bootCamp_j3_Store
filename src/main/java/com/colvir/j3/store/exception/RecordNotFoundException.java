package com.colvir.j3.store.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(final String message) {
        super(message);
    }
}
