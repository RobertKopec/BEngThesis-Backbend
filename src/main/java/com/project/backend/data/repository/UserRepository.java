package com.project.backend.data.repository;

import com.project.backend.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
    User findUserByUserID(long userID);
}
