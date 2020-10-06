package com.eigenbaumarkt.spring.restclientexamples.services.impl;

import com.eigenbaumarkt.spring.apitest.domain.User;
import com.eigenbaumarkt.spring.apitest.domain.UserData;
import com.eigenbaumarkt.spring.restclientexamples.services.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam("limit", limit);
        UserData userData = restTemplate.getForObject( uriBuilder.toUriString(), UserData.class);

        return userData.getData();
    }

    // using the new Spring framework 5 WebClient
    // and the Spring 5 reactive types Flux and Mono
    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {

        return WebClient
                .create(api_url)
                .get()
                // build the query URI:
                .uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                // now the exchange between WebClient and the Server-side it is hitting will take place:
                .exchange()
                // like line 36 (non-reactive API query getUsers)
                .flatMap(resp -> resp.bodyToMono(UserData.class))
                // like line 38
                .flatMapIterable(UserData::getData);

    }
}
