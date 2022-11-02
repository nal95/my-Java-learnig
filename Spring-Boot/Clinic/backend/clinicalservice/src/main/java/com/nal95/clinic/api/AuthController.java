package com.nal95.clinic.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nal95.clinic.dto.requests.UserRegistrationRequest;
import com.nal95.clinic.dto.responses.UserResponse;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.services.Auth;
import com.nal95.clinic.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class AuthController {
    private final UserService userService;
    private final Auth auth;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<String> signup(@RequestBody UserRegistrationRequest user){
        log.info("in SignUp Controller");
        boolean result = userService.saveUser(user);

        String returnValue = "";
        if (result){
            userService.signup(user);
            returnValue = "User Registration Successful !!";
        }else {
            returnValue = "Something append in the registration process :(";
        }
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping(value = "/role")
    public ResponseEntity<Role> createURole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(value = "/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm req){
        userService.addRoleToUser(req.getEmail(), req.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String username = decodedJWT.getSubject();

                UserResponse user = userService.getUser(username);

                String access_token = auth.createAccessToken(request,user,algorithm);
                auth.setHttpResponse(response, access_token, refresh_token, user);

            }catch (Exception exception) {
                log.info("ERROR Logging in: {}", exception.getMessage());
                auth.doFilterInternalErrorUtil(exception,response);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}

