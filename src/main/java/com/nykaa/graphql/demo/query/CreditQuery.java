package com.nykaa.graphql.demo.query;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nykaa.graphql.demo.service.CreditService;

@Service
public class CreditQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("creditTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private CreditService creditService;

    public String testCredit() {
        return creditService.testCredit();
    }
    
    public CompletableFuture<String> testCreditAsync() {
        return CompletableFuture.supplyAsync(() -> {
           return creditService.testCredit();
        }, taskExecutor);
    }
}
