package org.myongjimarket.dto;

import lombok.*;
import org.myongjimarket.domain.constant.Campus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;
    private Campus campus;
}
