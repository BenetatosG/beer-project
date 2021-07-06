package com.benet.demo.beer.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
public class CreateFavoriteBeerDTO {

    @NotNull(message = "Beer is required")
    BeerDTO beer;
    Boolean drunkBefore;
    String drunkBeerPlace;
    LocalDate drunkBeerDate;
    @Size(max = 5, message = "Rating can be between 0 and 5")
    Integer rating;
    String comments;
}
