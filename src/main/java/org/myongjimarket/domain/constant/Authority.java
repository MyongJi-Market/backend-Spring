package org.myongjimarket.domain.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum Authority {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

}
