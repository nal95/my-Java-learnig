package com.nal95.clinic.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class SecurityServiceImpl implements SecurityService{

   private final UserDetailsService userDetailsService;
   private final AuthenticationManager authenticationManager;

    public SecurityServiceImpl(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        log.info("New token for user: {}" + username + "and token ist: {} " + token.getCredentials());
        boolean result = token.isAuthenticated();

        if (result){
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return result;
    }
}
