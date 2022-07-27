package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.nykaa.graphql.demo.service.BuyAgainService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Service
public class BuyAgainQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("buyAgainTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private BuyAgainService buyAgainService;
    
    public CompletableFuture<JsonNode> buyAgain(String customerId, Integer customerGroupId, String categoryTagFilter) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return buyAgainService.getBuyAgainProducts(customerId, customerGroupId, categoryTagFilter);
        } catch (JsonProcessingException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }
}
