package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.UserDtoRequest;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.repos.RoleRepository;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.utils.UuidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private final UuidUtil uuidUtil;

    @Override
    public User saveUser(UserDtoRequest user) {

        log.info("Saving a new user");
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("DB already have this Email");

        User userToSave = new User();
        BeanUtils.copyProperties(user,userToSave);

        String publicUserId = uuidUtil.generateUserId(30);
        userToSave.setUserId(publicUserId);

        log.info("Saving a new user {}", userToSave);

        return userRepository.save(userToSave);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Adding new Role in db", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        String name = userRepository.findUserByEmail(email).getFirstName();
        log.info("Adding new Role {} to User {}", roleName, name);
        User user = userRepository.findUserByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user by email {}", email);
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

/*    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(User user){
        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("DB already have this Email");

        User savedUser = userRepository.save(user);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(savedUser,returnValue);

        return returnValue;
    }*/
}
