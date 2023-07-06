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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public CustomResponse addRefreshToken(Long memberId, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByMemberId(memberId);

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
}
