package com.benet.demo.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class StubService {

    @Value("classpath:data/beers.json")
    private Resource resourceFile;

    public void mockBeers() {

        stubFor(get(urlEqualTo("/v2/beers"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(readFile(resourceFile))));
    }

    private String readFile(Resource resourceFile) {
        try {
            return new String(Files.readAllBytes(resourceFile.getFile().toPath()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
