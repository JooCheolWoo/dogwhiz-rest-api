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

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        try {
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                //TODO 토큰 문자열이 Bearer null, Bearer undefined 등의 문자열로 왔을때에 대한 예외처리 필요함
                jwtToken = requestTokenHeader.substring(7);
                username = tokenProvider.getUsernameFromToken(jwtToken);


                // Once we get the token validate it.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                    // if token is valid configure Spring Security to manually set
                    // authentication
                    if (tokenProvider.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the
                        // Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }

                }
            }
        } catch (IllegalArgumentException e) {
            log.warn("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            log.warn("JWT Token has expired");
        } catch (Exception e) {
            log.warn("JWT Token not valid");
        }
        chain.doFilter(request, response);
    }
}