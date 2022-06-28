package com.nykaa.graphql.demo.dto.tradescheme;

import java.util.List;
import java.util.Map;

import com.nykaa.graphql.demo.dto.BaseResponse;

import lombok.Data;

@Data
public class GetAllOfferResponse extends BaseResponse {
    private Map<String, List<SchemeBasedOnSKUResponseDTO>> data;
}
