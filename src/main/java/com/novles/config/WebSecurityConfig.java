package com.novles.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novles.security.AjaxAuthenticationProvider;
import com.novles.security.AjaxLoginProcessingFilter;
import com.novles.security.CustomCorsFilter;
import com.novles.security.jwt.JwtConstants;
import com.novles.security.jwt.JwtFilter;
import com.novles.security.jwt.JwtProvider;
import com.novles.security.jwt.JwtRequestMatcher;
import com.novles.security.jwt.JwtTokenExtractor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllEndpointList = Arrays.asList(JwtConstants.AUTHENTICATION_URL,
                JwtConstants.REFRESH_TOKEN_URL, JwtConstants.GRAPHQL_GUI_URL, JwtConstants.GRAPHQL_SUBSCRIPTIONS_URL);
        List<String> apiEndPointList = Arrays.asList(JwtConstants.REST_API_ROOT_URL, JwtConstants.GRAPHQL_API_ROOT_URL);

        http.csrf().disable() // We don't need CSRF for JWT based authentication
//				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
//				.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()])).permitAll().and()
                .authorizeRequests().antMatchers(apiEndPointList.toArray(new String[apiEndPointList.size()]))
                .authenticated() // Protected API End-points
                .and().addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessingFilter(JwtConstants.AUTHENTICATION_URL),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtFilter(permitAllEndpointList, apiEndPointList),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtProvider);
    }
    
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/**");
    }

    protected JwtFilter buildJwtFilter(List<String> pathsToSkip, List<String> pattern) throws Exception {
        JwtRequestMatcher matcher = new JwtRequestMatcher(pathsToSkip, pattern);
        JwtFilter filter = new JwtFilter(failureHandler, jwtTokenExtractor, matcher);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler,
                failureHandler, objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
}