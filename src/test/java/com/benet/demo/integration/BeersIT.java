package com.benet.demo.integration;

import com.benet.demo.clients.punk.PunkApiClient;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BeersIT {

    @Autowired
    private PunkApiClient punkApiClient;

    @Test
    void test() {
        List<PunkBeerDTO> beers = punkApiClient.findBeers(null, null, "spicy");
        System.out.println(beers);
    }
}
