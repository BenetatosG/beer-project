package com.benet.demo.clients.common.exception;

import org.springframework.http.HttpStatus;

public class ClientErrorException extends RequestException {

    public ClientErrorException(HttpStatus responseStatus) {
        super(responseStatus);
    }
}
