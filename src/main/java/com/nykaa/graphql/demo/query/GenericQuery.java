package com.nykaa.graphql.demo.query;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nykaa.graphql.demo.dto.BaseResponse;
import com.nykaa.graphql.demo.service.GenericService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Service
public class GenericQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("genericTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private GenericService genericService;
    
    public CompletableFuture<HashMap<String, Object>> nestedApiCall(int customerGroupId, int productId) {
        return CompletableFuture.supplyAsync(() -> {
           try {
            return genericService.nestedAPICall(customerGroupId, productId);
        } catch (JsonProcessingException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
        }, taskExecutor);
    }

    public CompletableFuture<BaseResponse> explicitDataRequest() {
        return CompletableFuture.supplyAsync(() -> {
            BaseResponse baseResponse = new BaseResponse();
            Random random = new Random();
            baseResponse.setSuccess(random.nextBoolean());
            if(baseResponse.isSuccess()) {
                baseResponse.setStatusCode("200");
                baseResponse.setMessage("success");
            } else {
                baseResponse.setStatusCode("500");
                baseResponse.setMessage("error");
            }
            return baseResponse;
        }, taskExecutor);
    }
}
