package com.project.tracker.controller;

import com.project.tracker.entity.User;

import com.project.tracker.response.Utils;
import com.project.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class UserControllerImpl implements UserController{

    @Value("${upload.dir}")
    private String uploadDir;
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        try{
            return userService.signup(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();

        }
        return Utils.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return Utils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        try{
            return userService.getAllUsers();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<User> getUSerById(Long id) {
        User user=userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> update(User user) {
        User user1 = userService.getUserById(getCurrentUser().getBody().getId());
        if (user1== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the user fields based on the provided data
        user1.setContact(user.getContact());
        user1.setPermanentAddress(user.getPermanentAddress());
        user1.setPresentAddress(user.getPresentAddress());

        user1.setBloodGroup(user.getBloodGroup());
        user1.setDob(user.getDob());
        user1.setGender(user.getGender());
        user1.setTechnology(user.getTechnology());
        //user1.setUsername(user.getUsername());


        // Save the updated user
        User updatedEntity = userService.save(user1);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<User> getCurrentUser() {
        try {
            User currentUser = userService.getCurrentLoggedInUser();
            if (currentUser != null) {
                return new ResponseEntity<>(currentUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> uploadProfilePhoto(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);

            // Save the file to the upload directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the file path to the user's profile in the database
            Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPhoto(filename); // Save only the filename, adjust as needed
                userService.save(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
            }

            return ResponseEntity.ok("Photo uploaded and profile updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo.");
        }
    }


}
