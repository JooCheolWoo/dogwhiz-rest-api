package com.galaxypoby.dogwhiz.login;

import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.common.CustomResponse;
import com.galaxypoby.dogwhiz.login.dto.RequestLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CustomResponse memberLogin(@RequestBody RequestLoginDto request) throws CustomException {
        return loginService.loginMember(request);
    }
}
