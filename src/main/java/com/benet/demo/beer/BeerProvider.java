package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;

import java.util.List;

public interface BeerProvider {

    List<Beer> findBeers(Integer ibuLessThan, Integer ibuGreaterThan, String food);
}
