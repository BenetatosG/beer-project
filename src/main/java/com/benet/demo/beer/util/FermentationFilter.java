package com.benet.demo.beer.util;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.dto.FermentationType;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class FermentationFilter implements Predicate<Beer> {

    private final FermentationType fermentationType;

    @Override
    public boolean test(Beer beer) {
        return fermentationType == null || beer.getFermentationTemp() >= fermentationType.getLowerLimit()
                && beer.getFermentationTemp() <= fermentationType.getUpperLimit();
    }
}
