package com.nykaa.graphql.demo.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class GenericService {

    @Autowired
    PDPService pdpService;

    @Autowired
    TradeSchemeService tradeSchemeService;

    public HashMap<String, Object> nestedAPICall(int customerGroupId, int id) throws JsonProcessingException, URISyntaxException {
        HashMap<String, Object> pdpResponse = pdpService.getProducts(id, customerGroupId);
        List<String> skuList = new ArrayList<>();
        skuList.add(String.valueOf(pdpResponse.get("psku")));
        skuList.add(String.valueOf(pdpResponse.get("sku")));
        return tradeSchemeService.getAllOffer(customerGroupId, skuList);
    }

}
