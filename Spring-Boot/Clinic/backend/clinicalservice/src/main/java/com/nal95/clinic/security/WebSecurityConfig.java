package com.nal95.clinic.security;

import com.nal95.clinic.utils.AppRouteConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{

        //Configure AuthenticationManagerBuilder
/*        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);*/

        http.csrf().disable()
                .cors().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        AppRouteConstants.ADD_ROLE_TO_USER_URL,
                        AppRouteConstants.ROLE_URL).hasAnyAuthority("ROLE_DR_CHEF")
                .antMatchers(HttpMethod.GET, AppRouteConstants.USER_URL).hasAnyAuthority("ROLE_DR_CHEF")
                .antMatchers(HttpMethod.POST, AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.PATIENT_URL).hasAnyAuthority("ROLE_DR_CHEF", "ROLE_MED")
                .antMatchers(HttpMethod.GET, AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.PATIENT_ANALYZE_URL,
                        AppRouteConstants.PATIENTS_URL,
                        AppRouteConstants.PATIENT_URL,
                        AppRouteConstants.CLINICALS_URL).hasAnyAuthority("ROLE_DR_CHEF", "ROLE_MED")
                .antMatchers(HttpMethod.GET, AppRouteConstants.ROLE_URL).hasAuthority("ROLE_PASSIV")
/*                .antMatchers(AppRouteConstants.SIGN_UP_URL,
                        AppRouteConstants.SIGN_IN_URL,
                        AppRouteConstants.ADD_ROLE_TO_USER_URL,
                        AppRouteConstants.ROLE_URL,
                        AppRouteConstants.USER_URL,
                        AppRouteConstants.CLINICAL_URL,
                        AppRouteConstants.PATIENT_URL,
                        AppRouteConstants.PATIENT_ANALYZE_URL,
                        AppRouteConstants.PATIENTS_URL,
                        AppRouteConstants.CLINICALS_URL
                )
                .permitAll()*/
                .antMatchers(AppRouteConstants.SIGN_UP_URL,
                        AppRouteConstants.SIGN_IN_URL
                )
                .permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
