package com.originaldreams.usermanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MAMIAN
 */
@Component
@ConfigurationProperties(prefix = "token")
public class JwtProperties {

    private String secret;

    private Long expiration;

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}