package com.nykaa.graphql.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getForEntity(String url) {
        return restTemplate.getForEntity(url, String.class);
    }
}
