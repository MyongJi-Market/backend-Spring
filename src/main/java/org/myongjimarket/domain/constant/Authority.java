package org.myongjimarket.domain.constant;

import lombok.Getter;

@Getter
public enum Authority {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String role;

    Authority(String role) {
        this.role = role;
    }
}
