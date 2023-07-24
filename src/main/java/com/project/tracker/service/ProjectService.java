package com.project.tracker.service;

import com.project.tracker.entity.Projects;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    ResponseEntity<List<Projects>> getAllProjects();

    ResponseEntity<String> addProject(Map<String, String> requestMap);

    void deleteProject(Long id);
}
