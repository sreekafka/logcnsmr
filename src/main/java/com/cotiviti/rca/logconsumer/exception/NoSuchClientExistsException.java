package com.cotiviti.rca.logconsumer.exception;

public class NoSuchClientExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoSuchClientExistsException(String exception) {
        super(exception);
    }

}
