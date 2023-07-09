package com.galaxypoby.dogwhiz.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;
    private final TokenProvider tokenProvider;

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String servletPath = request.getServletPath();
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (servletPath.equals("/api/v1/login") || servletPath.equals("/api/v1/refresh")) {
            chain.doFilter(request, response);
        } else if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.info("JwtRequestFilter : JWT Token 존재하지 않습니다.");
        } else {
            try {
                // Access Token 가져오기
                String accessToken = authorizationHeader.substring(TOKEN_PREFIX.length());
                String username = null;

                if (accessToken.isEmpty()) {
                    log.info("JwtRequestFilter : JWT Token 잘못된 토큰입니다.");
                } else {
                    username = tokenProvider.getUsernameFromToken(accessToken);

                    if (authorizationHeader != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                        if (tokenProvider.validateToken(accessToken, userDetails)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                log.info("JwtRequestFilter : Token 확인이 불가능합니다.");
            } catch (ExpiredJwtException e) {
                log.info("JwtRequestFilter : Token 만료되었습니다.");
            } catch (Exception e) {
                log.info("JwtRequestFilter : 잘못된 Token 입니다.");
            }

        }
        chain.doFilter(request, response);
    }
}