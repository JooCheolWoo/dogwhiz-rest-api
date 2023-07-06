package com.galaxypoby.dogwhiz.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxypoby.dogwhiz.code.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        // Create your custom response
        CustomResponse customResponse = new CustomResponse(ErrorCode.MEMBER_FAIL_AUTHENTICATION);

        // Write the custom response body
        new ObjectMapper().writeValue(response.getWriter(), customResponse);
    }
}
