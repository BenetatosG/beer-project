package com.benet.demo.clients.punk;

import com.benet.demo.beer.BeerMapper;
import com.benet.demo.beer.BeerProvider;
import com.benet.demo.beer.domain.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PunkBeerProvider implements BeerProvider {

    private final PunkApiClient punkApiClient;

    @Override
    public List<Beer> findBeers(Integer ibuLessThan, Integer ibuGreaterThan, String food) {
        return punkApiClient.findBeers(ibuLessThan, ibuGreaterThan, food)
                .stream()
                .map(BeerMapper.INSTANCE::punkToDomain)
                .collect(Collectors.toList());
    }
}
