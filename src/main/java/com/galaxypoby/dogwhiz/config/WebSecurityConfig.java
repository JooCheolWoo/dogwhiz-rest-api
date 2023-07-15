package com.galaxypoby.dogwhiz.config;

import com.galaxypoby.dogwhiz.code.MemberCode;
import com.galaxypoby.dogwhiz.config.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] getPermitAll = {"/api/v1/banners",
                "/api/v1/members/valid/**",
                "/api/v1/members/verification"};
        String[] postPermitAll = {"/api/v1/login",
                "/api/v1/members",
                "/api/v1/refresh"};
        String[] patchPermitAll = {"/api/v1/boards"};
        String[] deletePermitAll = {};

        return httpSecurity
                .csrf().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, postPermitAll).permitAll()
                .antMatchers(HttpMethod.GET, getPermitAll).permitAll()
                .antMatchers(HttpMethod.PATCH, patchPermitAll).permitAll()
                .antMatchers(HttpMethod.DELETE, deletePermitAll).permitAll()
                .antMatchers("/test").hasAuthority(MemberCode.Role.ADMIN_MANAGER.name())
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

}
