package com.nal95.clinic.services;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.util.UuidUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    final
    UserRepository userRepository;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    final
    UuidUtils uuidUtils;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UuidUtils uuidUtils) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.uuidUtils = uuidUtils;
    }

    @Override
    public UserDto createUser(User user){
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("DB already have this Email");

        String publicUserId = uuidUtils.generateUserId(30);
        user.setUserId(publicUserId);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getEncryptedPassword()));
        User savedUser = userRepository.save(user);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser,returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email){
        User user = userRepository.findUserByEmail(email);
        if (user ==null) throw new UsernameNotFoundException(email);

        UserDto  returnValue = new UserDto();
        BeanUtils.copyProperties(user,returnValue);
        return returnValue;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.
                User( user.getEmail(),user.getEncryptedPassword(), new ArrayList<>());
    }
}
