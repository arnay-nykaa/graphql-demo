package com.nykaa.graphql.demo.util;

public class Constants {

    public interface TradeScheme {
        public interface Urls {
            String TEST_TS = "/testTradeScheme";
            String GET_ALL_OFFER = "/getAllOffer";
            String SCHEME_BASED_ON_SKU = "/schemeBasedOnSKU/";
            String V2 = "/v2";
        }
        String CUSTOMER_GROUP_ID = "customerGroupId";
        String SKU_LIST = "skuList";
    }

    public interface Credit {
        public interface Urls {
            String TEST_CREDIT = "/testCredit";
        }
    }

    public interface PDP {
        public interface Urls {
            String PRODUCT_LISTING = "/apis/v2/product.list";
            String PRODUCT_DETAILS = "/products/details";
            
        }
        String CUSTOMER_GROUP_ID = "customer_group_id";
        String ID = "id";
        String PRODUCT_ID = "product_id";
        String SKUS = "skus";
        String CATEGORY_TAG_FILTER = "category_tag_filter";
        String FETCH_ALL_DATA = "fetch_all_data";
        String DOMAIN = "domain";
        String MIN_ORDER_QTY = "min_order_qty";
        String MAX_ORDER_QTY = "max_allowed_qty";
    }

    public interface OMS {
        public interface Urls {
            String ORDER_LIST = "/v1/orderList";
        }
        String USER_ID = "userId";
        String ORDER_SOURCE = "orderSource";
        String DATA = "data";
        String ORDER_LIST = "orderList";
        String SHIPMENT_DETAIL = "shipmentDetail";
        String ITEM_LIST = "itemList";
        String ITEM_SKU = "itemSku";
        String PRODUCT_ID = "productId";
        String ITEM_NAME = "itemName";
        String IMAGE_URL = "imageUrl";
        String ITEM_QTY = "itemQuantity";
        String CREATED_AT = "createdAt";
    }

    public static final String INTROSPECTION = "Introspection";
    public static final String NYKAA_D = "NYKAA_D";
}
