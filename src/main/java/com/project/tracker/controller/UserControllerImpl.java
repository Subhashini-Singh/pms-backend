package com.project.tracker.controller;

import com.project.tracker.NotificationMessage;
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



    public NotificationMessage sendNewProjectNotification() {
        return new NotificationMessage("A new project has been added!");
    }


}
