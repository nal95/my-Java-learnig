package com.nal95.flightreservation.controllers;

import com.nal95.flightreservation.entities.User;
import com.nal95.flightreservation.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/userRegistration")
    public String showRegistrationPage(){
        LOGGER.info("Inside showRegistrationPage()");
        return "login/registerUser";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        LOGGER.info("Inside showLogin()");
        return "login/login";
    }

    @PostMapping(value = "/userLogin")
    public String register(@ModelAttribute("user") User user){
        LOGGER.info("Inside register()"+user);
        userRepository.save(user);
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){

        LOGGER.info("Inside login() and the email is:"+email);
        User user = userRepository.findByEmail(email);
       if(user.getPassword().equals(password)){
           return "findFlights";
       }else {
           modelMap.addAttribute("error", "Invalid user mail or Password. Please try again");
       }
        return "login/login";
    }
}
