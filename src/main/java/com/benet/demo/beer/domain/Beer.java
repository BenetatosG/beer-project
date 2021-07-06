package com.benet.demo.beer.domain;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Beer {

    @Id
    private final Long id;
    private final String name;
    private final Double fermentationTemp;
    private final Double ibu;
    @ElementCollection(targetClass=String.class)
    private final List<String> foodPairing;
}
