package com.benet.demo.clients.common.exception;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends RequestException{

    public ServerErrorException(HttpStatus responseStatus) {
        super(responseStatus);
    }
}
