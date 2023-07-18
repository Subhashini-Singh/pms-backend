package com.project.tracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String name;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "user_roles",
      //      joinColumns = @JoinColumn(name = "user_id"),
        //    inverseJoinColumns = @JoinColumn(name = "role_id"))
   // private Set<Role> roles = new HashSet<>();

    //@ManyToMany(mappedBy = "users")
    //@JsonManagedReference
    //private List<Program> program = new ArrayList<>();
}


