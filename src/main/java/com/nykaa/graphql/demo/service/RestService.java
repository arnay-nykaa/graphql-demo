package com.nykaa.graphql.demo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
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

    public ResponseEntity<Map<String, Object>> postForEntity(String url, Map<String, Object> request) throws URISyntaxException {
        RequestEntity<Map<String,Object>> requestEntity = new RequestEntity<Map<String,Object>>(request, HttpMethod.POST, new URI(url));
        return restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Map<String, Object>>() {
        });
    }
}
