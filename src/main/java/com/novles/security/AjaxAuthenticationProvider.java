package com.novles.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.novles.security.account.Account;
import com.novles.security.account.AccountMapper;
import com.novles.security.account.AccountRole;
import com.novles.security.account.Role;
import com.novles.security.jwt.entity.UserContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Assert.notNull(authentication, "No authentication data provided");

        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.debug("XID: " + id);
        log.debug("XPASSWORD: " + password);

        Account accountUser = Optional.ofNullable(accountMapper.findById(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));

        if (!passwordEncoder.matches(password, accountUser.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        log.debug("matches: " + passwordEncoder.matches(password, accountUser.getPassword()));

        List<AccountRole> roles = Lists.newArrayList();

        AccountRole role = new AccountRole();
        role.setRole(Role.SUPER_ADMIN);
        roles.add(role);

        accountUser.setRoles(roles);
        if (accountUser.getRoles() == null) {
            log.error("new InsufficientAuthenticationException");
            throw new InsufficientAuthenticationException("User has no roles assigned");
        }

        List<GrantedAuthority> authorities = accountUser.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(accountUser.getId(), authorities);
        log.debug("AjaxAuthenticationProvider : authenticate");
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
