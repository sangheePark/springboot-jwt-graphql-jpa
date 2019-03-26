package com.novles.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "web.jwt")
public class JwtProperties {
    /**
     * {@link JwtToken} will expire after this time.
     */
    private Integer expireTime;

    /**
     * Token issuer.
     */
    private String issuer;

    /**
     * Key is used to sign {@link JwtToken}.
     */
    private String signingKey;

    /**
     * {@link JwtToken} can be refreshed during this timeframe.
     */
    private Integer refreshExpireTime;
}
