package com.galaxypoby.dogwhiz.jwt;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.jwt.entity.RefreshToken;
import com.galaxypoby.dogwhiz.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public CustomResponse addRefreshToken(Long memberId, String token) {
        RefreshToken refreshToken = RefreshToken.builder()
                .memberId(memberId)
                .refreshToken(token)
                .build();

        refreshTokenRepository.save(refreshToken);

        return new CustomResponse(ErrorCode.OK);
    }
}
