package com.project.tracker.controller;

import com.project.tracker.entity.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@RequestBody User user);

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser();

    @PostMapping("/edit/photo")
    public ResponseEntity<String> uploadProfilePhoto(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId);


}
