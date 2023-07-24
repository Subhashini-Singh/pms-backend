package com.project.tracker.entity;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User implements Serializable {
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

    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "user_roles",
      //      joinColumns = @JoinColumn(name = "user_id"),
        //    inverseJoinColumns = @JoinColumn(name = "role_id"))
   // private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    @JsonManagedReference
    private List<Projects> project = new ArrayList<>();
}


