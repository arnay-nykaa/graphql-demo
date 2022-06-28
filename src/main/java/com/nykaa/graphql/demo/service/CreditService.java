package com.nykaa.graphql.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nykaa.graphql.demo.config.ApplicationProperties.CreditProperties;
import com.nykaa.graphql.demo.util.Constants.Credit;

@Service
public class CreditService {

    @Autowired
    private RestService restService;

    @Autowired
    private CreditProperties creditProperties;

    public String testCredit() {
        return restService.getForEntity(creditProperties.getUrl() + Credit.Urls.TEST_CREDIT, null, null).getBody();
    }

}
