package com.nal95.clinic.api;

import com.nal95.clinic.dto.requests.UserDtoRequest;
import com.nal95.clinic.dto.requests.UserLoginRequest;
import com.nal95.clinic.dto.responses.LoginResponse;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.services.SecurityService;
import com.nal95.clinic.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/login")
@Slf4j
public class UserController {
    private final UserService userService;

    private final SecurityService securityService;

    @PostMapping(value = "/user/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/login").toString());
        User userInDb = userService.getUser(user.getUsername());

        boolean loginResponse = securityService.login(user.getUsername(),user.getPassword());
        LoginResponse response = new LoginResponse();
        log.info("login boolean: " + loginResponse);

        log.info("login user: " + userInDb);

        if (loginResponse){
            BeanUtils.copyProperties(userInDb,response);
        }else {
            throw new  UsernameNotFoundException("the user is not aviable");
        }

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/user/registration")
    public ResponseEntity<User> createUsers(@RequestBody UserDtoRequest user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping(value = "/role")
    public ResponseEntity<Role> createURole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm req){
        userService.addRoleToUser(req.getEmail(), req.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}

