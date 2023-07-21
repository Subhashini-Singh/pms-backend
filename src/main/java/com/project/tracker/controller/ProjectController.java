package com.project.tracker.controller;

import com.project.tracker.entity.Projects;
import com.project.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectSer;

    @GetMapping("/getAllProjects")
    private ResponseEntity<List<Projects>> getAllProduct() {
        try{
            return projectSer.getAllProjects();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody Map<String, String> requestMap){
        try{
            return projectSer.addProject(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("There is internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
