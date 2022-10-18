package com.nal95.clinic.services;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;
import com.nal95.clinic.repos.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(User user){
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("DB already have this Email");

        User savedUser = userRepository.save(user);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser,returnValue);

        return returnValue;
    }
}
