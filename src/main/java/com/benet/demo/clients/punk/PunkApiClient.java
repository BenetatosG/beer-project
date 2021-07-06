package com.benet.demo.clients.punk;

import com.benet.demo.clients.common.configuration.CommonRestTemplateErrorHandler;
import com.benet.demo.clients.common.exception.TimeoutException;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Component
public class PunkApiClient {

    private final RestTemplate restTemplate;
    private final PunkApiConfig punkApiConfig;

    public PunkApiClient(RestTemplateBuilder restTemplateBuilder, PunkApiConfig punkApiConfig) {
        this.punkApiConfig = punkApiConfig;
        this.restTemplate = restTemplateBuilder
                .errorHandler(new CommonRestTemplateErrorHandler())
                .build();
    }


    public List<PunkBeerDTO> findBeers(Integer ibuLessThan, Integer ibuGreaterThan, String food) {
        UriComponents uri = createQueryParams(punkApiConfig.getUrl() + "/v2/beers", ibuLessThan, ibuGreaterThan, food);
        try {
            List<PunkBeerDTO> beers = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<PunkBeerDTO>>() {
            }).getBody();
            log.info("Found beers {}", beers);
            return beers;
        } catch (ResourceAccessException e) {
            throw new TimeoutException("Find beers", e);
        }
    }

    private UriComponents createQueryParams(String uri, Integer ibuLessThan, Integer ibuGreaterThan, String food) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        if (ibuLessThan != null) {
            builder.queryParam("ibu_lt", String.valueOf(ibuLessThan));
        }
        if (ibuGreaterThan != null) {
            builder.queryParam("ibu_gt", String.valueOf(ibuGreaterThan));
        }
        if (food != null) {
            builder.queryParam("food", food);
        }


        return builder.build();
    }
}
