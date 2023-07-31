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
    private ResponseEntity<List<Projects>> getAllProjects() {
        try{
            return projectSer.getAllProjects();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody Map<String, Object> requestMap){
        try{
            return projectSer.addProject(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("There is internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id){

        projectSer.deleteProject(id);
        return new ResponseEntity<>("{\"msg\":\"Project is deleted\"}", HttpStatus.OK);


    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Projects> getProjectById(@PathVariable Long projectId) {
        Projects project = projectSer.getProjectById(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }


   // @PostMapping
    //public ResponseEntity<Projects> saveProject(@RequestBody Projects project) {
      //  Projects savedProject = projectSer.saveProject(project);
        //return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    //}

    //@PutMapping("/{id}")
    //public ResponseEntity<Projects> updateProject(@PathVariable Long id,@RequestBody Projects projects){
      //  Projects pro = projectSer.getProjectById(id);

        //pro.setStatus("Completed"); // Update the project status
        //Projects updatedProject = projectSer.updateProject(pro);
        //return ResponseEntity.ok(updatedProject);

    @PutMapping("submit")
    public ResponseEntity<Projects> update(@RequestBody Projects project){
        Projects projects = projectSer.getProjectById(project.getId());
        if(projects==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projects.getSubmission_material();
        Projects updateSubmission = projectSer.saveProject(projects);
        return new ResponseEntity<>(updateSubmission,HttpStatus.OK);

    }



    }



