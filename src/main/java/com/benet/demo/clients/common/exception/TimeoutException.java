package com.benet.demo.clients.common.exception;

public class TimeoutException extends RuntimeException {

    public TimeoutException(String request, Throwable cause) {
        super(String.format("Timeout during %s request", request), cause);
    }
}
