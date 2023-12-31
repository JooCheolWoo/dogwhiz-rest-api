package com.galaxypoby.dogwhiz.login;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.config.jwt.TokenProvider;
import com.galaxypoby.dogwhiz.config.jwt.UserDetailServiceImpl;
import com.galaxypoby.dogwhiz.jwt.TokenService;
import com.galaxypoby.dogwhiz.jwt.dto.ResponseTokenDto;
import com.galaxypoby.dogwhiz.jwt.repository.RefreshTokenRepository;
import com.galaxypoby.dogwhiz.login.dto.RequestLoginDto;
import com.galaxypoby.dogwhiz.login.dto.ResponseLoginDto;
import com.galaxypoby.dogwhiz.member.entity.Member;
import com.galaxypoby.dogwhiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final TokenProvider tokenProvider;
    private final UserDetailServiceImpl userDetailService;
    private final TokenService tokenService;

    @Transactional
    public CustomResponse loginMember(RequestLoginDto request) throws CustomException {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_EXIST));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.MEMBER_PASSWORD_NOT_MATCH);
        }

        member.login();

        ResponseLoginDto response = modelMapper.map(member, ResponseLoginDto.class);

        UserDetails userDetails = userDetailService.loadUserByUsername(member.getEmail());
        String accessToken = tokenProvider.generateAccessToken(userDetails);
        ResponseTokenDto tokenDto = new ResponseTokenDto();
        tokenDto.setAccessToken(accessToken);
        tokenDto.setExpiredDate(tokenProvider.getExpirationDateFromToken(accessToken).getTime());

        response.setTokenInfo(tokenDto);
        tokenService.addRefreshToken(member.getId(), userDetails);


        return new CustomResponse(ErrorCode.OK, response);
    }
}
