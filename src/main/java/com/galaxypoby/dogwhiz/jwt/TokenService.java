package com.galaxypoby.dogwhiz.jwt;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.config.jwt.TokenProvider;
import com.galaxypoby.dogwhiz.jwt.dto.RequestTokenDto;
import com.galaxypoby.dogwhiz.jwt.dto.ResponseTokenDto;
import com.galaxypoby.dogwhiz.jwt.entity.RefreshToken;
import com.galaxypoby.dogwhiz.jwt.repository.RefreshTokenRepository;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final MemberRepository memberRepository;

    public CustomResponse addRefreshToken(Long memberId, UserDetails userDetails) {
        RefreshToken refreshToken = refreshTokenRepository.findByMemberId(memberId);
        String token = tokenProvider.generateRefreshToken(userDetails);

        if (refreshToken == null) {
            refreshToken = RefreshToken.builder()
                    .memberId(memberId)
                    .refreshToken(token)
                    .build();
        } else {
            refreshToken.updateToken(token);
        }

        refreshTokenRepository.save(refreshToken);

        return new CustomResponse(ErrorCode.OK);
    }

    public CustomResponse refresh(RequestTokenDto request) throws CustomException {

        if (!request.getToken().equals(refreshTokenRepository.findByMemberId(request.getMemberId()))) {
            throw new CustomException(ErrorCode.MEMBER_ABNORMAL_REFRESH);
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        UserDetails userDetails = userDetailsService.loadUserByUsername(member.getEmail());

        if (!tokenProvider.validateToken(request.getToken(), userDetails)) {
            throw new CustomException(ErrorCode.MEMBER_ABNORMAL_REFRESH);
        }

        String accessToken = tokenProvider.generateAccessToken(userDetails);

        long refreshExpireTime = tokenProvider.getExpirationDateFromToken(request.getToken()).getTime();
        if ((refreshExpireTime - new Date().getTime()) < (24 * 60 * 60 * 1000)) {
            addRefreshToken(request.getMemberId(), userDetails);
        }

        ResponseTokenDto response = new ResponseTokenDto();
        response.setAccessToken(accessToken);

        return new CustomResponse(ErrorCode.OK, response);
    }
}
