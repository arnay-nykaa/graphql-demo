package com.nykaa.graphql.demo.query;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.nykaa.graphql.demo.service.CreditService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Service
public class CreditQuery implements GraphQLQueryResolver {

    @Autowired
    @Qualifier("creditTaskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private CreditService creditService;

    public String testCredit() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return creditService.testCredit();
    }
    
    public CompletableFuture<String> testCreditAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           return creditService.testCredit();
        }, taskExecutor);
    }
}
