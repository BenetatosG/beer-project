package com.benet.demo.clients.common.exception;

import org.springframework.http.HttpStatus;

public class RequestException extends RuntimeException {
    HttpStatus responseStatus;

    public RequestException(HttpStatus responseStatus) {
        super(String.format("Client request exception, returned status %s", responseStatus));
        this.responseStatus = responseStatus;
    }
}
