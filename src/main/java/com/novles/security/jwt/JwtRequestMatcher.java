package com.novles.security.jwt;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JwtRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;
    private OrRequestMatcher processingMatchers;

    public JwtRequestMatcher(List<String> pathsToSkip, List<String> pathsToProcessing) {
        List<RequestMatcher> skipList = pathsToSkip.stream().map(path -> new AntPathRequestMatcher(path))
                .collect(Collectors.toList());
        List<RequestMatcher> processingList = pathsToProcessing.stream().map(path -> new AntPathRequestMatcher(path))
                .collect(Collectors.toList());
        matchers = new OrRequestMatcher(skipList);
        processingMatchers = new OrRequestMatcher(processingList);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)) {
            return false;
        }
        return processingMatchers.matches(request) ? true : false;
    }
}
