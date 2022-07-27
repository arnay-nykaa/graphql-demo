package com.nykaa.graphql.demo.service;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nykaa.graphql.demo.config.ApplicationProperties.OMSProperties;
import com.nykaa.graphql.demo.dto.SkuInfo;
import com.nykaa.graphql.demo.util.Constants;
import com.nykaa.graphql.demo.util.Constants.OMS;

@Service
public class BuyAgainService {

    @Autowired
    private RestService restService;

    @Autowired
    private OMSProperties omsProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PDPService pdpService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public JsonNode getBuyAgainProducts(String customerId, Integer customerGroupId, String categoryTagFilter)  throws JsonProcessingException, URISyntaxException {
        Map<String, SkuInfo> skuList = new HashMap<>();
        JsonNode previousOrders = getPreviousOrdersFromOMS(customerId);
        getSkuListFromPreviousOrders(skuList, previousOrders);
        getPDPInfo(skuList, customerGroupId, categoryTagFilter);
        return objectMapper.valueToTree(skuList);
    }

    private void getPDPInfo(Map<String, SkuInfo> skuList, Integer customerGroupId, String categoryTagFilter) throws JsonProcessingException {
        for (SkuInfo product : skuList.values()) {
            JsonNode productDetails = pdpService.getProductDetails(product.getProductId(), customerGroupId, categoryTagFilter).path("response");
            product.setMinOrderQty(productDetails.get("min_order_qty").asInt());
            product.setMaxOrderQty(productDetails.get("max_allowed_qty").asInt());
        }
        
    }

    private JsonNode getPreviousOrdersFromOMS(String customerId) throws JsonMappingException, JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(OMS.ORDER_SOURCE, Constants.NYKAA_D);
        params.put(OMS.USER_ID, customerId);
        String responseString = restService.getForEntity(omsProperties.getUrl() + OMS.Urls.ORDER_LIST, null, params).getBody();
        return objectMapper.readTree(responseString);
    }

    private void getSkuListFromPreviousOrders(Map<String, SkuInfo> skuList, JsonNode previousOrders) {
        JsonNode orderList = previousOrders.path("data").path("orderList");
        if (orderList.isArray()) {
            for (JsonNode order : orderList) {
                JsonNode shipmentDetail = order.path("shipmentDetail");
                if (shipmentDetail.isArray()) {
                    for (JsonNode shipment : shipmentDetail) {
                        JsonNode itemList = shipment.path("itemList");
                        if (itemList.isArray()) {
                            for (JsonNode item : itemList) {
                                addSkuDataToList(item, order, shipment, skuList);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addSkuDataToList(JsonNode item, JsonNode order, JsonNode shipment, Map<String, SkuInfo> skuList) {
        String sku = item.get("itemSku").asText();
        SkuInfo skuInfo = skuList.containsKey(sku) ? skuList.get(sku) : new SkuInfo(sku);
        if (!skuList.containsKey(sku)) {
            skuInfo.setProductId(item.get("productId").asText());
            skuInfo.setName(item.get("itemName").asText());
            skuInfo.setImageUrl(item.get("imageUrl").asText());
        }
        skuInfo.setAggregatedQtyOrdered(skuInfo.getAggregatedQtyOrdered() + item.get("itemQuantity").asInt());
        skuInfo.setNoOfOrders(skuInfo.getNoOfOrders() + 1);
        skuInfo.setAverageQtyOrdered(skuInfo.getAggregatedQtyOrdered() / skuInfo.getNoOfOrders());
        LocalDate orderDate = LocalDate.parse(order.get("createdAt").asText(), formatter);
        if (Objects.isNull(skuInfo.getLastOrdered()) || skuInfo.getLastOrdered().isBefore(orderDate)) {
            skuInfo.setLastOrdered(orderDate);
        }
        skuList.put(sku, skuInfo);
    }
}
