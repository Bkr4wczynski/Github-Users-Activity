package com.Bartek.springActivity.rest;

import org.springframework.web.client.RestClient;

public class ApiCaller {
    RestClient restClient = RestClient.create();

    public String callGetRequest(String uri) {
        return restClient.get()
                .uri("https://api.github.com/"+uri)
                .retrieve()
                .body(String.class);
    }
}
