package com.nykaa.graphql.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SkuInfo {

    private String productId;
    private String name;
    private String sku;
    int aggregatedQtyOrdered = 0;
    int averageQtyOrdered;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate lastOrdered;
    String imageUrl;
    double mrp;
    double profitMargin;
    double dealerPrice;
    int minOrderQty;
    int maxOrderQty;
    int inventoryStatus;
    @JsonIgnore
    int noOfOrders = 0;

    public SkuInfo(String sku) {
        this.sku = sku;
    }
}
