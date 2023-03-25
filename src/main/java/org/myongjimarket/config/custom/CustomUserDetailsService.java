package org.myongjimarket.config.custom;

import lombok.RequiredArgsConstructor;
import org.myongjimarket.domain.Member;
import org.myongjimarket.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(this::changeToUserDetails)
                .orElseThrow(
                        () -> new UsernameNotFoundException("해당 이메일의 유저를 찾을 수 없습니다.")
                );
    }

    private UserDetails changeToUserDetails(Member member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(new SimpleGrantedAuthority(member.getAuthority().toString()))
                .build();
    }
}
