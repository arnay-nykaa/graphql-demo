package com.nykaa.graphql.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class GenericService {

    @Autowired
    PDPService pdpService;

    public HashMap<String, Object> nestedAPICall(int customerGroupId, int id) throws JsonProcessingException {
        return pdpService.getProducts(id, customerGroupId);
    }

}
