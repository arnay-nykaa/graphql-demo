package com.nykaa.graphql.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties
@Data
@Component
public class ApplicationProperties {

    @ConfigurationProperties(prefix = "tradescheme")
    @Getter
    @Setter
    @Component
    public class TradeSchemeProperties {
        private String url;
    }

    @ConfigurationProperties(prefix = "credit")
    @Getter
    @Setter
    @Component
    public class CreditProperties {
        private String url;
    }

    @ConfigurationProperties(prefix = "discovery")
    @Getter
    @Setter
    @Component
    public class DiscoveryProperties {
        private String nykaaApiUrl;
    }

    @ConfigurationProperties(prefix = "cache")
    @Getter
    @Setter
    @Component
    public class CacheProperties {
        private Long ttlInMinutes;
        private Long maxSize;
    }

    @ConfigurationProperties(prefix = "oms")
    @Getter
    @Setter
    @Component
    public class OMSProperties {
        private String url;
    }
}
