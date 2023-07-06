package com.galaxypoby.dogwhiz.config.jwt;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.entity.Role;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.MEMBER_NOT_EXIST.getMessage()));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : member.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        for (String role : member.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
