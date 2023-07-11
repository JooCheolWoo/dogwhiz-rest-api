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

        final String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String accessToken = null;
        String username = null;

        try {

            if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                accessToken = authorizationHeader.substring(TOKEN_PREFIX.length());
                username = tokenProvider.getUsernameFromToken(accessToken);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                if (tokenProvider.validateToken(accessToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

        } catch (IllegalArgumentException e) {
            log.info("JwtRequestFilter : Token 확인이 불가능합니다.");
        } catch (ExpiredJwtException e) {
            log.info("JwtRequestFilter : Token 만료되었습니다.");
        } catch (Exception e) {
            log.info("JwtRequestFilter : " + e.getMessage());
        }
        chain.doFilter(request, response);
    }
}