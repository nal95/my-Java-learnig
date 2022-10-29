package com.nal95.clinic.security;

import com.nal95.clinic.services.UserService;
import com.nal95.clinic.utils.AppRouteConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig (UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{

        //Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        final CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager);
        filter.setFilterProcessesUrl(AppRouteConstants.SIGN_IN_URL);

        http.csrf().disable()
                .cors().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        AppRouteConstants.ADD_ROLE_TO_USER_URL,
                        AppRouteConstants.ROLE_URL).hasAuthority("ROLE_DR_CHEF")
                .antMatchers(HttpMethod.GET, AppRouteConstants.USER_URL).hasAuthority("ROLE_DR_CHEF")
                .antMatchers(HttpMethod.POST, AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.PATIENT_URL).hasAuthority("ROLE_MED")
                .antMatchers(HttpMethod.GET, AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.PATIENT_ANALYZE_URL,
                        AppRouteConstants.PATIENTS_URL,
                        AppRouteConstants.PATIENT_URL,
                        AppRouteConstants.CLINICALS_URL).hasAuthority("ROLE_MED")
                .antMatchers(HttpMethod.POST, AppRouteConstants.SIGN_UP_URL).permitAll()
                .antMatchers(AppRouteConstants.SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.GET, AppRouteConstants.ROLE_URL).hasAuthority("ROLE_PASSIV")
                .anyRequest().authenticated()
                .and().addFilter(filter);

        return http.build();
    }

}
