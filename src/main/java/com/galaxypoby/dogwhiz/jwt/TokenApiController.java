package com.galaxypoby.dogwhiz.jwt;

import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.jwt.dto.RequestTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public CustomResponse reGenerateToken(@RequestBody RequestTokenDto request) throws CustomException {
        return tokenService.refresh(request);
    }
}
