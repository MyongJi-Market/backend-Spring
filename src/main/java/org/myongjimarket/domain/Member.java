package org.myongjimarket.domain;

import lombok.*;
import org.myongjimarket.domain.constant.Rating;
import org.myongjimarket.domain.constant.Campus;
import org.myongjimarket.dto.SignupRequestDto;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

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

    @Enumerated(EnumType.STRING)
    private Rating rating;


    /**
     * @param requestDto 회원가입 요청 Dto
     * Rating 은 무조건 브론즈 등급부터 시작.
     */
    public static Member toEntity(SignupRequestDto requestDto) {
        return Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .username(requestDto.getUsername())
                .campus(requestDto.getCampus())
                .rating(Rating.BRONZE)
                .build();
    }

}
