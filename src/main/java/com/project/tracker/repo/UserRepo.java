package com.project.tracker.repo;

import com.project.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
    List<User> findAll();
}
