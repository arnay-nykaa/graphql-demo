package com.nykaa.graphql.demo.controller;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.benmanes.caffeine.cache.Cache;
import com.nykaa.graphql.demo.cache.RequestKey;

import graphql.kickstart.servlet.cache.CachedResponse;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private Cache<RequestKey, CachedResponse> responseCache;

    @GetMapping("/all")
    public ResponseEntity<ConcurrentMap<RequestKey, CachedResponse>> getAllCaches() {
        return new ResponseEntity<ConcurrentMap<RequestKey, CachedResponse>>(responseCache.asMap(), HttpStatus.OK);
    }
    
    @PostMapping("/invalidate")
    public ResponseEntity<String> invalidateEntry(@RequestBody List<String> queries) {
        responseCache.invalidate(new RequestKey(queries));
        return new ResponseEntity<String>("Entry invalidated successfully", HttpStatus.OK);
    }
}
