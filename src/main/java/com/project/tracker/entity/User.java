package com.project.tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.management.relation.Role;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String username;

    @NaturalId
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String role;
    private String status;
    private String gender;
    private ArrayList<String> technology = new ArrayList<>();
    private LocalDate dob;
    private String contact;
    private String permanentAddress;
    private String presentAddress;
    private String bloodGroup;
    private String photo;

    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "user_roles",
      //      joinColumns = @JoinColumn(name = "user_id"),
        //    inverseJoinColumns = @JoinColumn(name = "role_id"))
   // private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Projects> project = new ArrayList<>();
}


