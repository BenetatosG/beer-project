package com.benet.demo.beer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class FavoriteBeerDTO {

    Long id;
    BeerDTO beer;
    Boolean drunkBefore;
    String drunkBeerPlace;
    LocalDate drunkBeerDate;
    Integer rating;
    String comments;
}
