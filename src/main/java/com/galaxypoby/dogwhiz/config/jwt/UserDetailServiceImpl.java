package com.galaxypoby.dogwhiz.config.jwt;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.Common;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.MEMBER_NOT_EXIST.getMessage()));

        try {
            Common.checkStatus(member);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        member.getMemberRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
