package com.project.tracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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



    private LocalDate start_date;


    private LocalDate end_date;

    @ManyToMany
    @JsonManagedReference
    private List<User> users;
}
