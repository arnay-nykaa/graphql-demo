package com.nykaa.graphql.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType json() {
        return ExtendedScalars.Json;
    }
}
