package com.nykaa.graphql.demo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<String> getForEntity(String url, HttpHeaders headers, Map<String,String> queryParams) {
        if (!CollectionUtils.isEmpty(queryParams)) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
            return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class, queryParams);
        }
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers), String.class);
    }

    public ResponseEntity<String> postForEntity(String url, JsonNode request) throws URISyntaxException {
        RequestEntity<JsonNode> requestEntity = new RequestEntity<JsonNode>(request, HttpMethod.POST, new URI(url));
        return restTemplate.exchange(requestEntity, String.class);
    }
}
