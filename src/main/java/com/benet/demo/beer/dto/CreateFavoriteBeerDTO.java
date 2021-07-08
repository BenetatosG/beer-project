package com.benet.demo.beer.dto;

import com.benet.demo.beer.validation.FirstOrder;
import com.benet.demo.beer.validation.SecondOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.apache.commons.lang3.StringUtils;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
@GroupSequence({FirstOrder.class, SecondOrder.class, CreateFavoriteBeerDTO.class})
public class CreateFavoriteBeerDTO {

    @NotNull(groups = FirstOrder.class, message = "Beer is required")
    CreateBeerDTO beer;
    @NotNull(groups = FirstOrder.class)
    Boolean drunkBefore;
    String drunkBeerPlace;
    LocalDate drunkBeerDate;
    @With
    @Min(groups = SecondOrder.class, value = 0, message = "Rating can not be lower than 0")
    @Max(groups = SecondOrder.class, value = 5, message = "Rating can not be higher than 5")
    Integer rating;
    String comments;

    @AssertTrue(groups = SecondOrder.class, message = "Can't set beer qualifiers if beer not drunken before")
    public boolean isDrunkBefore() {
        if(!drunkBefore) {
            return StringUtils.isBlank(drunkBeerPlace) && drunkBeerDate == null && rating == null;
        } else
            return true;
    }
}
