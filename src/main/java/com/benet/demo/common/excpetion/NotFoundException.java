package com.benet.demo.common.excpetion;

public class NotFoundException extends BusinessException {

    public NotFoundException(String resourceName, Long id) {
        super(String.format("Resource %s with id %s was not found", resourceName, id));
    }
}
