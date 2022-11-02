package com.nal95.clinic.security;

import com.nal95.clinic.security.filters.CustomAuthenticationFilter;
import com.nal95.clinic.security.filters.CustomAuthorizationFilter;
import com.nal95.clinic.services.UserService;
import com.nal95.clinic.utils.AppRouteConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .antMatchers(
                        AppRouteConstants.SIGN_IN_URL,
                        AppRouteConstants.SIGN_UP_URL,
                        AppRouteConstants.REFRESH_TOKEN
                ).permitAll()
                .antMatchers(
                        AppRouteConstants.USER_URL,
                        AppRouteConstants.ROLE_URL,
                        AppRouteConstants.ADD_ROLE_TO_USER_URL,
                        AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.CLINICAL_DATE_URL
                ).hasAnyAuthority("ROLE_DR_CHEF", "ROLE_MED")
                .antMatchers(
                        AppRouteConstants.PATIENT_URL,
                        AppRouteConstants.PATIENTS_URL
                ).hasAnyAuthority("ROLE_DR_CHEF", "ROLE_MED", "ROLE_PASSIVE")
                .anyRequest().authenticated()
                .and().addFilter(filter).addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
