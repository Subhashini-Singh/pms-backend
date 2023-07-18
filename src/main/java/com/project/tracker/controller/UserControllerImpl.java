package com.project.tracker.controller;

import com.project.tracker.entity.User;

import com.project.tracker.response.Utils;
import com.project.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController{
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
}
