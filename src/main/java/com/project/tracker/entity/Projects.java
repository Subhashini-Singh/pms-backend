package com.project.tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String project_name;
    private String details;
    private String status;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
