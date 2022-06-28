package com.nykaa.graphql.demo.dto.tradescheme;

import java.util.Map;

import lombok.Data;

@Data
public class SchemeBasedOnSKUResponseDTO {
    private Integer indexing;
    private String schemeType;
    private Long ruleId;
    private MarginPercentageDto marginPercentage;
    private MarginPercentageDto freeQuantity;
    private String currentMsg;
    private String nextMsg;
    private Long customerSegment;
    private Long baseQuantity;
    private String cluster;
    private Boolean isParent;
    private Map<String, SchemeBasedOnSKUResponseDTO> child;
}
