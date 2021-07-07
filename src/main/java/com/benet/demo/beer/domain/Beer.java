package com.benet.demo.beer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Beer name can not be empty")
    private String name;
    private Double fermentationTemp;
    private Double ibu;
    @ElementCollection(targetClass=String.class)
    private List<String> foodPairing;
}
