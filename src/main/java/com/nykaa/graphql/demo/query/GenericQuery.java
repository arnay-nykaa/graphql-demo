package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nykaa.graphql.demo.service.GenericService;

@Service
public class GenericQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("creditTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private GenericService genericService;
    
    public CompletableFuture<HashMap<String, Object>> nestedApiCall(int customerGroupId, int id) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return genericService.nestedAPICall(customerGroupId, id);
        } catch (JsonProcessingException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }
}
