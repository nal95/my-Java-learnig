package com.nal95.flightreservation.controllers;

import com.nal95.flightreservation.entities.User;
import com.nal95.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    @RequestMapping("/userRegistration")
    public String showRegistrationPage(){
        return "login/registerUser";
    }

    @PostMapping(value = "/userLogin")
    public String register(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){
       User user = userRepository.findByEmail(email);
       if(user.getPassword().equals(password)){
           return "findFlights";
       }else {
           modelMap.addAttribute("error", "Invalid user mail or Password. Please try again");
       }
        return "login/login";
    }
}
