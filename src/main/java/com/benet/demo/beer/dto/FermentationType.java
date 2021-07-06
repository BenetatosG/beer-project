package com.benet.demo.beer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FermentationType {

    TOP(10, 25), BOTTOM(7, 15);

    private final int lowerLimit;
    private final int upperLimit;
}
