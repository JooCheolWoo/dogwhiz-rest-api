package com.galaxypoby.dogwhiz.config.jwt;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.code.StatusCode;
import com.galaxypoby.dogwhiz.code.RoleCode;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.MEMBER_NOT_EXIST.getMessage()));

        List<RoleCode> roles = member.getRoles().stream()
                .map(role -> role.getStatusCode() == StatusCode.APPROVED ? role.getRoleCode() : null)
                .collect(Collectors.toList());

        List<GrantedAuthority> authorities;
        authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
