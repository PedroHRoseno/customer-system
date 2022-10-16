package com.customersystem.poc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class PostalCodeExternalApiService {
    @Autowired
    WebClient webClient;

    public Mono<String> getPostalCode(String postalCode) {
        return webClient.get()
                .uri("/" + postalCode + "/json")
                .retrieve()
                .bodyToMono(String.class);
    }
}
