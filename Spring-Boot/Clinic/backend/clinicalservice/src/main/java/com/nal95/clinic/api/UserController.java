package com.nal95.clinic.api;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;
import com.nal95.clinic.dto.responses.UserRest;
import com.nal95.clinic.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @GetMapping(value = "/registration")
    public String register(){
        return "User Registration";
    }

    @PostMapping(value = "/login")
    public UserRest login(@RequestBody User user){
        UserRest returnValue = new UserRest();

        UserDto createUser = userService.createUser(user);
        BeanUtils.copyProperties(createUser,returnValue);

        return returnValue;
    }
}
