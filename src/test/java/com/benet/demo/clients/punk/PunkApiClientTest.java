package com.benet.demo.clients.punk;

import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static com.benet.demo.TestData.mockPunkBeerDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest
@ContextConfiguration(classes = {PunkApiClient.class, PunkApiConfig.class})
class PunkApiClientTest {

    @Autowired
    private PunkApiClient client;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PunkApiConfig config;

    @Test
    @DisplayName("Given request to find beers, when it's success, then result is list of beers")
    void testGetBeers() {
        //Arrange
        server.expect(requestTo(config.getUrl() + "/v2/beers"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(asJson(List.of(mockPunkBeerDTO())), MediaType.APPLICATION_JSON));

        //Act
        List<PunkBeerDTO> beers = client.findBeers(null, null, null);

        //Assert
        server.verify();
        assertThat(beers).hasSize(1);
        PunkBeerDTO punkBeerDTO = beers.get(0);
        assertThat(punkBeerDTO).hasNoNullFieldsOrProperties();
    }

    @Test
    void testGetFilterIbuLt() {
        //Arrange
        server.expect(requestTo(config.getUrl() + "/v2/beers?ibu_lt=20"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        //Act
        client.findBeers(20, null, null);

        //Assert
        server.verify();
    }

    @Test
    void testGetFilterIbuGt() {
        //Arrange
        server.expect(requestTo(config.getUrl() + "/v2/beers?ibu_gt=20"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        //Act
        client.findBeers(null, 20, null);

        //Assert
        server.verify();
    }

    @Test
    void testGetFilterIbuFood() {
        //Arrange
        server.expect(requestTo(config.getUrl() + "/v2/beers?food=spicy"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        //Act
        client.findBeers(null, null, "spicy");

        //Assert
        server.verify();
    }

    private String asJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}