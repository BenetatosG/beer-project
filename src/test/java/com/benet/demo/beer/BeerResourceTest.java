package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.beer.dto.FermentationType;
import com.benet.demo.common.config.SpringFoxConfig;
import com.benet.demo.common.excpetion.NotFoundException;
import com.benet.demo.integration.BeersSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.benet.demo.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {BeersSystem.class, BeerResource.class, ObjectMapper.class, BeerAdvice.class})
class BeerResourceTest {

    @Autowired
    private BeersSystem beersSystem;
    @MockBean
    private BeerService beerService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    void testError() {
        FermentationType fermentationType = FermentationType.TOP;
        Integer ibuLt = 10;
        Integer ibuGt = 5;
        String food = "spicy";

        when(beerService.findBeers(fermentationType, ibuLt, ibuGt, food)).thenThrow(new RuntimeException());

        beersSystem.findBeers(fermentationType, ibuLt, ibuGt, food, status().isInternalServerError());
    }

    @Test
    void testGetBeers() {
        FermentationType fermentationType = FermentationType.TOP;
        Integer ibuLt = 10;
        Integer ibuGt = 5;
        String food = "spicy";

        Beer beer = mockBeer();
        when(beerService.findBeers(fermentationType, ibuLt, ibuGt, food)).thenReturn(List.of(beer));

        ResultMatcher expectedJson = content().json(asJson(List.of(BeerMapper.INSTANCE.beerToDTO(beer))));
        beersSystem.findBeers(fermentationType, ibuLt, ibuGt, food, status().isOk(), expectedJson);
    }

    @Test
    void testNotFoundException() {

        when(beerService.getFavoriteBeer(99L)).thenThrow(new NotFoundException("Favorite Beer", 99L));

        beersSystem.getFavoriteBeer(99L, status().isNotFound());

    }

    @Test
    void testNotValidRating() {
        CreateFavoriteBeerDTO createFavoriteBeerDTO = mockCreateFavoriteBeerDTO();

        beersSystem.saveFavoriteBeer(createFavoriteBeerDTO.withRating(6), status().isBadRequest());
    }

    @Test
    void testNotValidDrunkBefore() {
        CreateFavoriteBeerDTO createFavoriteBeerDTO = mockCreateFavoriteBeerDTONotValidDrunk();

        beersSystem.saveFavoriteBeer(createFavoriteBeerDTO, status().isBadRequest());
    }



    @SneakyThrows
    private String asJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}