package com.benet.demo.clients.punk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PunkBeerDTO {

    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    double fermentationTemp;
    double ibu;
    @JsonProperty("food_pairing")
    List<String> foodPairing;

    @JsonSetter("method")
    public void setMethod(JsonNode method) {
        fermentationTemp = method.get("fermentation").get("temp").get("value").asDouble();
    }
}
