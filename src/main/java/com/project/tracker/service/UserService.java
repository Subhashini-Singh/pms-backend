package com.project.tracker.service;

import com.project.tracker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {


    ResponseEntity<String> signup(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<?> getAllUsers();
}
