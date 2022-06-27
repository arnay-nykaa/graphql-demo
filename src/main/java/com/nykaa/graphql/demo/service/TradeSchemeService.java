package com.nykaa.graphql.demo.service;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nykaa.graphql.demo.config.ApplicationProperties.TradeSchemeProperties;
import com.nykaa.graphql.demo.util.Constants.TradeScheme;

@Service
public class TradeSchemeService {

    @Autowired
    private RestService restService;

    @Autowired
    private TradeSchemeProperties tradeSchemeProperties;
    
    public String testTradeScheme() {
        return restService.getForEntity(tradeSchemeProperties.getUrl() + TradeScheme.Urls.TEST_TS).getBody();
    }

    public Map<String,Object> getAllOffer(int customerGroupId, List<String> skuList) throws URISyntaxException {
        Map<String, Object> request = new HashMap<>();
        request.put(TradeScheme.CUSTOMER_GROUP_ID, customerGroupId);
        request.put(TradeScheme.SKU_LIST, skuList);
        return restService.postForEntity(tradeSchemeProperties.getUrl() + TradeScheme.Urls.GET_ALL_OFFER, request).getBody();
    }

}
