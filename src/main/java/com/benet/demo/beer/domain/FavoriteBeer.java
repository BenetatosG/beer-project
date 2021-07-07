package com.benet.demo.beer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBeer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Beer beer;
    private Boolean drunkBefore;
    private String drunkBeerPlace;
    private LocalDate drunkBeerDate;
    @Min(value = 0, message = "Rating can not be lower than 0")
    @Max(value = 5, message = "Rating can not be higher than 5")
    private Integer rating;
    private String comments;
}
