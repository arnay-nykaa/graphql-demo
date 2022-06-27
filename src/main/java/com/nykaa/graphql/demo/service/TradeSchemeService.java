package com.nykaa.graphql.demo.service;

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
        return restService.getForEntity(tradeSchemeProperties.getUrl() + TradeScheme.TEST_TS).getBody();
    }

}
