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
        }
        String CUSTOMER_GROUP_ID = "customer_group_id";
        String PRODUCT_ID = "id";
    }

    public static final String INTROSPECTION = "Introspection";
}
