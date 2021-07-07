package com.benet.demo.integration;

import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class BeersSystem {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public BeersSystem(WebApplicationContext context, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @SneakyThrows
    public List<BeerDTO> findBeers() {
        String contentAsString = mockMvc.perform(get("/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public void saveFavoriteBeer(FavoriteBeerDTO dto) {
        mockMvc.perform(post("/beers/favorites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(dto)))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    public List<FavoriteBeerDTO> findFavoriteBeers() {
        String contentAsString = mockMvc.perform(get("/beers/favorites")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
    }

    @SneakyThrows
    private String asJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
