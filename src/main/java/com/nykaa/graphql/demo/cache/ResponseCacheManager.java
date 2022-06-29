package com.nykaa.graphql.demo.cache;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.nykaa.graphql.demo.util.Constants;

import graphql.kickstart.execution.input.GraphQLInvocationInput;
import graphql.kickstart.servlet.cache.CachedResponse;
import graphql.kickstart.servlet.cache.GraphQLResponseCacheManager;

@Component
public class ResponseCacheManager implements GraphQLResponseCacheManager {

    @Autowired
    private Cache<RequestKey, CachedResponse> responseCache;

    @Override
    public CachedResponse get(HttpServletRequest request, GraphQLInvocationInput invocationInput) {
        return responseCache.getIfPresent(new RequestKey(invocationInput.getQueries()));
    }

    @Override
    public boolean isCacheable(HttpServletRequest request, GraphQLInvocationInput invocationInput) {
        // Do not cache introspection query
        return invocationInput.getQueries().stream().noneMatch(this::isIntrospectionQuery);
    }

    @Override
    public void put(HttpServletRequest request, GraphQLInvocationInput invocationInput, CachedResponse cachedResponse) {
        responseCache.put(new RequestKey(invocationInput.getQueries()), cachedResponse);
    }

    private boolean isIntrospectionQuery(String query) {
        return query.contains(Constants.INTROSPECTION);
    }
}
