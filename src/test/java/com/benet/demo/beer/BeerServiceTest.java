package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.dto.FermentationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.benet.demo.TestData.mockBeer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeerServiceTest {

    @InjectMocks
    private BeerService sut;
    @Mock
    private BeerProvider beerProvider;

    @Test
    void testFilter() {
        //Arrange
        Beer topBeer = mockBeer(25.0);
        Beer bottomBeer = mockBeer(10.0);
        when(beerProvider.findBeers(null, null, null)).thenReturn(List.of(topBeer, bottomBeer));

        //Act
        List<Beer> beers = sut.findBeers(FermentationType.BOTTOM, null, null, null);

        //Assert
        assertThat(beers).hasOnlyOneElementSatisfying(beer -> assertThat(beer.getFermentationTemp()).isEqualTo(10.0));
    }
}