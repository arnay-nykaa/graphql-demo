package com.nykaa.graphql.demo.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class GenericService {

    @Autowired
    PDPService pdpService;

    @Autowired
    TradeSchemeService tradeSchemeService;

    public JsonNode nestedAPICall(int customerGroupId, int productId) throws JsonProcessingException, URISyntaxException {
        JsonNode pdpResponse = pdpService.getProducts(productId, customerGroupId);
        List<String> skuList = new ArrayList<>();
        skuList.add(pdpResponse.get("result").get("psku").asText());
        skuList.add(pdpResponse.get("result").get("sku").asText());
        return tradeSchemeService.getAllOffer(customerGroupId, skuList);
    }

}
