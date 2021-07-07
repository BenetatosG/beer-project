package com.benet.demo.integration;

import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.clients.punk.PunkApiClient;
import com.benet.demo.integration.config.StubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeersIT {

    @Autowired
    private PunkApiClient punkApiClient;
    @Autowired
    private StubService stubService;
    @Autowired
    private BeersSystem beersSystem;

    @Test
    void testIntegration() {
        //Get beers from mocked provider
        stubService.mockBeers();
        List<BeerDTO> beers = beersSystem.findBeers();
        BeerDTO beerDTO = beers.get(0);

        //Save first beer to favorites
        FavoriteBeerDTO favoriteBeerDTO = FavoriteBeerDTO.builder()
                .beer(beerDTO)
                .drunkBeerDate(LocalDate.now())
                .drunkBeerPlace("Bucharest")
                .rating(5)
                .comments("Fine beer")
                .drunkBefore(false)
                .build();
        beersSystem.saveFavoriteBeer(favoriteBeerDTO);

        //Get favorite beers
        List<FavoriteBeerDTO> favoriteBeers = beersSystem.findFavoriteBeers();

        assertThat(favoriteBeers).hasSize(1);

        FavoriteBeerDTO dto = favoriteBeers.get(0);

        assertThat(dto.getComments()).isEqualTo(favoriteBeerDTO.getComments());
        assertThat(dto.getDrunkBeerDate()).isEqualTo(favoriteBeerDTO.getDrunkBeerDate());
        assertThat(dto.getDrunkBeerPlace()).isEqualTo(favoriteBeerDTO.getDrunkBeerPlace());
        assertThat(dto.getDrunkBefore()).isEqualTo(favoriteBeerDTO.getDrunkBefore());
    }
}
