package com.originaldreams.usermanager.util;

import org.springframework.stereotype.Component;

@Component
public class TokenDetail {
    private String username;

    public TokenDetail(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
