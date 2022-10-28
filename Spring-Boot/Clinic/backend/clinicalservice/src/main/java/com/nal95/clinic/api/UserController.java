package com.nal95.clinic.api;

import com.nal95.clinic.dto.requests.UserDtoRequest;
import com.nal95.clinic.dto.responses.UserRest;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @GetMapping(value = "/user/registration")
    public String register(){
        return "User Registration";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<User> createUsers(@RequestBody UserDtoRequest user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping(value = "/role/save")
    public ResponseEntity<Role> createURole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(value = "/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm req){
        userService.addRoleToUser(req.getEmail(), req.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/login")
    public UserRest login(@RequestBody User user){
        UserRest returnValue = new UserRest();

/*        UserDto createUser = userService.createUser(user);
        BeanUtils.copyProperties(createUser,returnValue);*/

        return returnValue;
    }

}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}

