package com.project.backend.data.repository;

import com.project.backend.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUserId(long userId);
    void removeUserByUserId(long userId);
}
