package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nykaa.graphql.demo.service.TradeSchemeService;

@Service
public class TradeSchemeQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("asyncExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private TradeSchemeService tradeSchemeService;
    
    public CompletableFuture<String> testTradeScheme() {
        return CompletableFuture.supplyAsync(() -> {
           return tradeSchemeService.testTradeScheme();
        }, taskExecutor);
    }

    public CompletableFuture<Map<String,Object>> getAllOffer(int customerGroupId, List<String> skuList) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return tradeSchemeService.getAllOffer(customerGroupId, skuList);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }
}
