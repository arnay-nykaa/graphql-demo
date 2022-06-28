package com.nykaa.graphql.demo.util;

public class Constants {

    public interface TradeScheme {
        public interface Urls {
            public static final String TEST_TS = "/testTradeScheme";
            public static final String GET_ALL_OFFER = "/getAllOffer";
            public static final String SCHEME_BASED_ON_SKU = "/schemeBasedOnSKU/";
            public static final String V2 = "/v2";
        }
        public static final String CUSTOMER_GROUP_ID = "customerGroupId";
        public static final String SKU_LIST = "skuList";
    }

    public interface Credit {
        public interface Urls {
            public static final String TEST_CREDIT = "/testCredit";
        }
    }

    public interface PDP {
        public interface Urls {
            public static final String PRODUCT_LISTING = "/apis/v2/product.list";
        }
        public static final String CUSTOMER_GROUP_ID = "customer_group_id";
        public static final String ID = "id";
    }
}
