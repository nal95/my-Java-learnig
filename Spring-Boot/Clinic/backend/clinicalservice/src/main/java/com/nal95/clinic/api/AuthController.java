package com.nal95.clinic.api;


import com.nal95.clinic.dto.request.UserLoginRequest;
import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.dto.response.AuthenticationResponse;
import com.nal95.clinic.dto.response.RegistrationResponse;
import com.nal95.clinic.dto.response.UserResponse;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.services.AuthServie;
import com.nal95.clinic.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthServie authServie;



    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody UserRegistrationRequest user){
        boolean result = userService.saveUser(user);

        String returnValue = "";
        if (result){
            authServie.signup(user);
            returnValue = "User Registration Successful !!";
        }else {
            returnValue = "Somthing apend in the registraion proccess :(";
        }
        return new ResponseEntity<>(returnValue,HttpStatus.OK);

    }

    @GetMapping(value = "/user-verification/{token}")
    public ResponseEntity<String> verification(@PathVariable String token){
        boolean result = authServie.verifyAccount(token);
        String returnValue = "";

        if (result){
            returnValue = "Account Activated Successful !!";
        }else {
            returnValue = "Somthing apend in the activation proccess :(";
        }

        return new ResponseEntity<>(returnValue,HttpStatus.OK);

    }
    @PostMapping(value = "/login")
    public AuthenticationResponse login(@RequestBody UserLoginRequest user){
        return authServie.loing(user);
/*        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/login").toString());
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

        return ResponseEntity.created(uri).body(response);*/

    }

    @PostMapping(value = "registration")
    public ResponseEntity<RegistrationResponse> createUsers(@RequestBody UserRegistrationRequest user){
/*        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));*/
        return null;
    }

    @PostMapping(value = "/create-role")
    public ResponseEntity<Role> createURole(@RequestBody Role role){
/*        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));*/
        return null;
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm req){
/*        userService.addRoleToUser(req.getEmail(), req.getRoleName());
        return ResponseEntity.ok().build();*/
        return null;
    }
}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
