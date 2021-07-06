package com.benet.demo.common.excpetion;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
