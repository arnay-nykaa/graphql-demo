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
}
