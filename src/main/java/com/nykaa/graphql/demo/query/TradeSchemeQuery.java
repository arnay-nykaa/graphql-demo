package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.nykaa.graphql.demo.service.TradeSchemeService;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Service
public class TradeSchemeQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("tradeSchemeTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private TradeSchemeService tradeSchemeService;

    public String testTradeScheme(DataFetchingEnvironment env) {
        GraphQLServletContext context = env.getContext();
        HttpServletRequest httpRequest = context.getHttpServletRequest();
        Map<String, String> headers = Collections.list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, httpRequest::getHeader));
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
