package com.benet.demo.beer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBeer {

    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Beer beer;
    private Boolean drunkBefore;
    private String drunkBeerPlace;
    private LocalDate drunkBeerDate;
    @Size(max = 5)
    private Integer rating;
    private String comments;
}
