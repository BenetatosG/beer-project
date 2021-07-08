package com.benet.demo;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.dto.CreateBeerDTO;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class TestData {

    public static Double TEMP = 20.0;

    public static PunkBeerDTO mockPunkBeerDTO() {
        return new PunkBeerDTO(1L, "PunkBeer", 19.0, 5.0, List.of("food"));
    }

    public static Beer mockBeer() {
       return mockBeer(TEMP);
    }

    public static Beer mockBeer(Double fermentationTemp) {
        return new Beer(1L, "PunkBeer", fermentationTemp, 5.0, List.of("food"));
    }

    public static CreateFavoriteBeerDTO mockCreateFavoriteBeerDTO() {
        return CreateFavoriteBeerDTO.builder()
                .drunkBeerDate(LocalDate.now())
                .drunkBeerPlace("home")
                .comments("good")
                .drunkBefore(true)
                .rating(1)
                .beer(new CreateBeerDTO("NiceBeer", 1.0, 2.0, List.of("shrimp")))
                .build();
    }

    public static CreateFavoriteBeerDTO mockCreateFavoriteBeerDTONotValidDrunk() {
        return CreateFavoriteBeerDTO.builder()
                .drunkBeerDate(LocalDate.now())
                .drunkBeerPlace("home")
                .comments("good")
                .drunkBefore(false)
                .rating(1)
                .beer(new CreateBeerDTO("NiceBeer", 1.0, 2.0, List.of("shrimp")))
                .build();
    }
}
