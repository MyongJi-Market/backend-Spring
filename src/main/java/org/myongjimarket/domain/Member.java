package org.myongjimarket.domain;

import lombok.*;
import org.myongjimarket.domain.base.BaseEntity;
import org.myongjimarket.domain.constant.Authority;
import org.myongjimarket.domain.constant.Rating;
import org.myongjimarket.domain.constant.Campus;
import org.myongjimarket.dto.MemberResponseDto;
import org.myongjimarket.dto.SignupRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Campus campus;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Rating rating = Rating.BRONZE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;


    /**
     * @param requestDto 회원가입 요청 Dto
     * Rating 은 무조건 브론즈 등급부터 시작.
     */
    public static Member toEntity(SignupRequestDto requestDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .username(requestDto.getUsername())
                .campus(requestDto.getCampus())
                .build();
    }

    public MemberResponseDto toResponseDto() {
        return MemberResponseDto.builder()
                .email(getEmail())
                .username(getUsername())
                .campus(getCampus())
                .build();
    }

}
