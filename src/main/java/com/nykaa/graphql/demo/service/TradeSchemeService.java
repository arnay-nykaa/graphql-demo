package com.nykaa.graphql.demo.service;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nykaa.graphql.demo.config.ApplicationProperties.TradeSchemeProperties;
import com.nykaa.graphql.demo.util.Constants.TradeScheme;

@Service
public class TradeSchemeService {

    @Autowired
    private RestService restService;

    @Autowired
    private TradeSchemeProperties tradeSchemeProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

    public String testTradeScheme() {
        return restService.getForEntity(tradeSchemeProperties.getUrl() + TradeScheme.Urls.TEST_TS, null, null).getBody();
    }

//    @Cacheable(value = "getAllOffer", key = "{#request}")
    public HashMap<String, Object> getAllOffer(String request) throws URISyntaxException, JsonProcessingException {
        String responseString = restService
                .postForEntity(getUrl(TradeScheme.Urls.GET_ALL_OFFER),objectMapper.readValue(request, typeRef))
                .getBody();
        return objectMapper.readValue(responseString, typeRef);
    }

    public HashMap<String, Object> getAllOffer(int customerGroupId, List<String> skuList) throws URISyntaxException, JsonProcessingException {
        Map<String, Object> request = new HashMap<>();
        request.put(TradeScheme.CUSTOMER_GROUP_ID, customerGroupId);
        request.put(TradeScheme.SKU_LIST, skuList);
        String responseString = restService.postForEntity(getUrl(TradeScheme.Urls.GET_ALL_OFFER), request).getBody();
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        return objectMapper.readValue(responseString, typeRef);
    }

    public HashMap<String, Object> schemeBasedOnSKU(String request, String version)
            throws URISyntaxException, JsonProcessingException {
        String responseString = restService.postForEntity(
                getUrl(TradeScheme.Urls.SCHEME_BASED_ON_SKU,
                        version.equals("v2") ? TradeScheme.Urls.V2 : StringUtils.EMPTY),
                objectMapper.readValue(request, typeRef)).getBody();
        return objectMapper.readValue(responseString, typeRef);
    }

    private String getUrl(String... paths) {
        StringBuilder url = new StringBuilder();
        url.append(tradeSchemeProperties.getUrl());
        for (String path : paths) {
            url.append(path);
        }
        return url.toString();
    }
}
