package com.Bartek.springActivity.rest;

import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

public class ApiCaller {
    RestClient restClient = RestClient.create();

    public List callGetRequest(String uri) {
        return restClient.get()
                .uri("https://api.github.com/"+uri)
                .retrieve()
                .body(List.class);
    }
}
