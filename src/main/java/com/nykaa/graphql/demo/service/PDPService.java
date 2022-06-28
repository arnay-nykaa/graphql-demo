package com.nykaa.graphql.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nykaa.graphql.demo.config.ApplicationProperties.PDPProperties;
import com.nykaa.graphql.demo.util.Constants.PDP;

@Service
public class PDPService {

    @Autowired
    private RestService restService;

    @Autowired
    private PDPProperties pdpProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private final TypeReference<HashMap<String, Object>> mapRef = new TypeReference<HashMap<String, Object>>() {};
    
    public HashMap<String, Object> getProducts(int id, int customerGroupId) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(PDP.CUSTOMER_GROUP_ID, String.valueOf(customerGroupId));
        params.put(PDP.ID, String.valueOf(id));
        String responseString = restService.getForEntity(pdpProperties.getUrl() + PDP.Urls.PRODUCT_LISTING, null, params).getBody();
        return objectMapper.readValue(responseString, mapRef);
    }

}
