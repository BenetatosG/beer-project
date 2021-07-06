package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.domain.FavoriteBeer;
import com.benet.demo.beer.dto.FermentationType;
import com.benet.demo.beer.util.FavoriteBeerRepository;
import com.benet.demo.beer.util.FermentationFilter;
import com.benet.demo.common.excpetion.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService {

    private final BeerProvider beerProvider;
    private final FavoriteBeerRepository favoriteBeerRepository;

    public List<Beer> findBeers(FermentationType fermentationType, Integer ibuLessThan, Integer ibuGreaterThan, String food) {
        return beerProvider.findBeers(ibuLessThan, ibuGreaterThan, food).stream()
                .filter(new FermentationFilter(fermentationType))
                .collect(Collectors.toList());
    }

    public Long saveFavoriteBeer(FavoriteBeer favoriteBeer) {
        return favoriteBeerRepository.save(favoriteBeer).getId();
    }

    public FavoriteBeer getFavoriteBeer(Long id) {
        return favoriteBeerRepository.findById(id).orElseThrow(() -> new NotFoundException("Beer", id));
    }

    public List<FavoriteBeer> getAllFavoriteBeers() {
        return favoriteBeerRepository.findAll();
    }
}
