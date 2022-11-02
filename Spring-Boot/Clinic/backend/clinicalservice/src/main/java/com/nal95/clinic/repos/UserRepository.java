package com.nal95.clinic.repos;

import com.nal95.clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);

}
