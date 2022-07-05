package com.nykaa.graphql.demo.cache;

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class RequestKey implements Serializable {

    private static final long serialVersionUID = -2022840667725356646L;
    List<String> queries;

}
