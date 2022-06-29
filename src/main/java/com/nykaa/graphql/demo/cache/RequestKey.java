package com.nykaa.graphql.demo.cache;

import java.util.List;

import lombok.Value;

@Value
public class RequestKey {

    List<String> queries;

}
