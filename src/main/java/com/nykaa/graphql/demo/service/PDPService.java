package com.nykaa.graphql.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nykaa.graphql.demo.config.ApplicationProperties.PDPProperties;
import com.nykaa.graphql.demo.util.Constants;
import com.nykaa.graphql.demo.util.Constants.PDP;

@Service
public class PDPService {

    @Autowired
    private RestService restService;

    @Autowired
    private PDPProperties pdpProperties;

    @Autowired
    private ObjectMapper objectMapper;

    public JsonNode getProducts(int productId, int customerGroupId) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(PDP.CUSTOMER_GROUP_ID, String.valueOf(customerGroupId));
        params.put(PDP.ID, String.valueOf(productId));
        String responseString = restService.getForEntity(pdpProperties.getUrl() + PDP.Urls.PRODUCT_LISTING, null, params).getBody();
        return objectMapper.readTree(responseString);
    }
    
    public JsonNode getProductDetails(String productId, int customerGroupId, String categoryTagFilter) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(PDP.CUSTOMER_GROUP_ID, String.valueOf(customerGroupId));
        params.put(PDP.PRODUCT_ID, productId);
        params.put(PDP.FETCH_ALL_DATA, String.valueOf(true));
        params.put(PDP.CATEGORY_TAG_FILTER, categoryTagFilter);
        params.put(PDP.DOMAIN, Constants.NYKAA_D);
        String responseString = restService.getForEntity(pdpProperties.getUrl() + PDP.Urls.PRODUCT_DETAILS, null, params).getBody();
        return objectMapper.readTree(responseString);
    }
}
