package com.nal95.clinic.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nal95.clinic.dto.responses.UserResponse;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.security.filters.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class Auth {
    private final UserRepository userRepository;

    public void doFilterInternalErrorUtil(Exception exception, HttpServletResponse response) throws IOException {
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());

        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    public String createAccessToken(HttpServletRequest request, UserResponse userResponse, Algorithm algorithm) {

        User user = userRepository.findUserByUsername(userResponse.getUsername());

        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);

    }

    public void setHttpResponse(HttpServletResponse response, String access_token, String refresh_token, UserResponse user) {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        tokens.put("UUID",  user.getUserId());
        response.setContentType(APPLICATION_JSON_VALUE);
    }
}
