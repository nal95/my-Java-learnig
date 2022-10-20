package com.nal95.clinic.api;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;
import com.nal95.clinic.model.responses.UserRest;
import com.nal95.clinic.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    final
    UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public String register(){
        return "User Registration";
    }

    @PostMapping(value = "/users")
    public UserRest login(@RequestBody User user){
        UserRest returnValue = new UserRest();

        UserDto createUser = userService.createUser(user);
        BeanUtils.copyProperties(createUser,returnValue);

        return returnValue;
    }
}
