package org.myongjimarket.dto;

import lombok.*;
import org.myongjimarket.domain.constant.Campus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDto {
    private String email;
    private String password;
    private String username;
    private Campus campus;
}
