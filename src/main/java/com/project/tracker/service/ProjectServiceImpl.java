package com.project.tracker.service;

import com.project.tracker.entity.Projects;
import com.project.tracker.jwt.JwtFilter;
import com.project.tracker.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<List<Projects>> getAllProjects() {
        try{
            return new ResponseEntity<>(projectRepo.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addProject(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isTeamLead()){
                if(this.validateProjectMap(requestMap,false)){
                    projectRepo.save(this.getProjectFromMap(requestMap,false));
                    return new ResponseEntity<>("New Product was added successfully",HttpStatus.OK);
                }
                return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<>("Unauthorized Access",HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProjectMap(Map<String,String> requestMap,boolean validateId){
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id")&& validateId){
                return true;
            }else return !validateId;
        }
        return false;
    }

    private Projects getProjectFromMap(Map<String, String> requestMap, boolean isAdd) {
        Projects project=new Projects();
        if(isAdd){
            project.setId(Integer.parseInt(requestMap.get("id")));
        }else project.setStatus("true");
        project.setProject_name(requestMap.get("name"));
        project.setDetails(requestMap.get("description"));
        project.setStart_date(LocalDateTime.parse(requestMap.get("date")));
        project.setEnd_date(LocalDateTime.parse(requestMap.get("date")));
        return project;

    }
}
