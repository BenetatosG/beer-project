package com.benet.demo.beer.dto;

import lombok.Value;

import java.util.List;

@Value
public class CreateBeerDTO {

    String name;
    Double fermentationTemp;
    Double ibu;
    List<String> foodPairing;
}
