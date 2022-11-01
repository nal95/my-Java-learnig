package com.nal95.clinic;

import com.nal95.clinic.dto.requests.UserDtoRequest;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.services.UserService;
import com.nal95.clinic.utils.Title;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ClinicalserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicalserviceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

/*    @Bean
    CommandLineRunner runner(UserService userService){

        return args -> {

            userService.saveRole(new Role(1, "ROLE_DR_CHEF"));
            userService.saveRole(new Role(2, "ROLE_MED"));
            userService.saveRole(new Role(3, "ROLE_PASSIV"));

            userService.saveUser(new UserDtoRequest("Nal", "alex", "nal@wed.de", "test", Title.Dr, null, false, new ArrayList<>()));
            userService.saveUser(new UserDtoRequest("Müller", "Karl", "karl@wed.de", "test", Title.Med, null, false, new ArrayList<>()));
            userService.saveUser(new UserDtoRequest("Ali", "momo", "momo@wed.de", "test", Title.Med, null, false, new ArrayList<>()));
            userService.saveUser(new UserDtoRequest("Schwarznager", "arnold", "arnold@wed.de", "test", Title.Med, null, false, new ArrayList<>()));

            userService.addRoleToUser("nal@wed.de", "ROLE_DR_CHEF");
            userService.addRoleToUser("arnold@wed.de", "ROLE_DR_CHEF");
            userService.addRoleToUser("karl@wed.de", "ROLE_MED");
            userService.addRoleToUser("Momo@wed.de", "ROLE_PASSIV");
        };
    }*/
}
