package com.nykaa.graphql.demo.dto;

import lombok.Data;

@Data
public class BaseResponse {

    private boolean success;
    private String message;
    private String statusCode;
}
