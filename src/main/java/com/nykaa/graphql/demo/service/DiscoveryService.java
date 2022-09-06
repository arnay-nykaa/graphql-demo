package com.nykaa.graphql.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nykaa.graphql.demo.config.ApplicationProperties.DiscoveryProperties;
import com.nykaa.graphql.demo.util.Constants;
import com.nykaa.graphql.demo.util.Constants.NYKAA_API;

@Service
public class DiscoveryService {

    @Autowired
    private RestService restService;

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Autowired
    private ObjectMapper objectMapper;

    public JsonNode getProducts(int productId, int customerGroupId) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(NYKAA_API.CUSTOMER_GROUP_ID, String.valueOf(customerGroupId));
        params.put(NYKAA_API.ID, String.valueOf(productId));
        String responseString = restService.getForEntity(discoveryProperties.getNykaaApiUrl() + NYKAA_API.Urls.PRODUCT_LISTING, null, params).getBody();
        return objectMapper.readTree(responseString);
    }
    
    public JsonNode getProductDetails(String productId, int customerGroupId, String categoryTagFilter) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(NYKAA_API.CUSTOMER_GROUP_ID, String.valueOf(customerGroupId));
        params.put(NYKAA_API.PRODUCT_ID, productId);
        params.put(NYKAA_API.FETCH_ALL_DATA, String.valueOf(true));
        params.put(NYKAA_API.CATEGORY_TAG_FILTER, categoryTagFilter);
        params.put(NYKAA_API.DOMAIN, Constants.NYKAA_D);
        String responseString = restService.getForEntity(discoveryProperties.getNykaaApiUrl() + NYKAA_API.Urls.PRODUCT_DETAILS, null, params).getBody();
        return objectMapper.readTree(responseString);
    }
}
