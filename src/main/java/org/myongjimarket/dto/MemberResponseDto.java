package org.myongjimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.myongjimarket.domain.constant.Campus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private String email;
    private String username;
    private Campus campus;
}
