package com.store.cosystore.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, DELIVERY;

    @Override
    public String getAuthority() {
        return name();
    }
}
