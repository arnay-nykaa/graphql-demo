package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.nykaa.graphql.demo.service.TradeSchemeService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Service
public class TradeSchemeQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("tradeSchemeTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private TradeSchemeService tradeSchemeService;

    public String testTradeScheme() {
        return tradeSchemeService.testTradeScheme();
    }

    public CompletableFuture<String> testTradeSchemeAsync() {
        return CompletableFuture.supplyAsync(() -> {
           return tradeSchemeService.testTradeScheme();
        }, taskExecutor);
    }

    public CompletableFuture<JsonNode> getAllOffer(JsonNode request) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return tradeSchemeService.getAllOffer(request);
        } catch (URISyntaxException|JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }

    public CompletableFuture<JsonNode> schemeBasedOnSKU(JsonNode request, String version) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return tradeSchemeService.schemeBasedOnSKU(request, version);
        } catch (URISyntaxException|JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }
}
