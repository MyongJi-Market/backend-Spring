package org.myongjimarket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginInfo {
    private String email;
    private String token;
}
