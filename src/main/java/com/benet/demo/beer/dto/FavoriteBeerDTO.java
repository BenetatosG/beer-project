package com.benet.demo.beer.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
public class FavoriteBeerDTO {

    Long id;
    @NotNull
    BeerDTO beer;
    Boolean drunkBefore;
    String drunkBeerPlace;
    LocalDate drunkBeerDate;
    @Size(max = 5)
    Integer rating;
    String comments;
}
