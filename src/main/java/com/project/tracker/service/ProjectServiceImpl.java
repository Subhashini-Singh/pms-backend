package com.project.tracker.service;

import com.project.tracker.entity.Projects;
import com.project.tracker.entity.User;
import com.project.tracker.jwt.JwtFilter;
import com.project.tracker.repo.ProjectRepo;
import com.project.tracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

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
    public ResponseEntity<String> addProject(Map<String, Object> requestMap) {
        try{
            if(jwtFilter.isTeamLead()){
                if(this.validateProjectMap(requestMap)){
                    projectRepo.save(this.getProjectFromMap(requestMap));
                    String resp = "{\"msg\":\"New Project was added successfully\"}";
                    return new ResponseEntity<>(resp,HttpStatus.OK);

                }
                return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<>("Unauthorized Access",HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);

    }


    private boolean validateProjectMap(Map<String,Object> requestMap){
        if(requestMap.containsKey("project_name") && requestMap.containsKey("details") && requestMap.containsKey("start_date")){
            //todo: check for unique project
            return true;
        };
        return false;
    }

    private Projects getProjectFromMap(Map<String, Object> requestMap) {
        Projects project = new Projects();
        project.setProject_name((String) requestMap.get("project_name"));
        project.setDetails((String) requestMap.get("details"));
        project.setStart_date(LocalDate.parse((String) requestMap.get("start_date")));
        project.setEnd_date(LocalDate.parse((String) requestMap.get("end_date")));

        // Initialize the users list in the project entity
        project.setUsers(new ArrayList<>());

        List<Map<String, Object>> usersMap = (List<Map<String, Object>>) requestMap.get("users");
        if (usersMap != null) {
            for (Map<String, Object> userMap : usersMap) {
                Long userId = Long.parseLong(userMap.get("id").toString());
                User fetchedUser = userRepo.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                project.getUsers().add(fetchedUser);
            }
        }

        return project;
    }
}
