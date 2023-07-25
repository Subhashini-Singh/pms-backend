package com.project.tracker.service;

import com.project.tracker.entity.Projects;
import com.project.tracker.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> signup(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<?> getAllUsers();

    public User getUserById(Long id);

}
