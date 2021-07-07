package com.benet.demo.integration.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
public class WireMockConfig {

    @Value("${wiremock.host}")
    private String host;
    @Value("${wiremock.port}")
    private Integer port;

    @Bean(destroyMethod = "stop")
    WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", port);
        return wireMockServer;
    }

}
