package com.project.tracker.controller;

import com.project.tracker.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RequestMapping("/api/v1")
public interface UserController {
    @CrossOrigin
    @PostMapping("/auth/register")
    public ResponseEntity<String> signup(@RequestBody Map<String,String> requestMap);
    @PostMapping("/auth/authenticate")
    public ResponseEntity<String> login (@RequestBody Map<String,String> requestMap);

    @GetMapping("/userDetails")
    public ResponseEntity<?> getAllUsers();

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUSerById(@PathVariable Long id);

}
