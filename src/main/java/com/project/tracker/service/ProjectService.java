package com.project.tracker.service;

import com.project.tracker.entity.Projects;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    ResponseEntity<List<Projects>> getAllProjects();

    ResponseEntity<String> addProject(Map<String, Object> requestMap);

    void deleteProject(Long id);

    public Projects getProjectById(Long projectId);

    public Projects saveProject(Projects project);

    public Projects updateProject(Projects project);
}
