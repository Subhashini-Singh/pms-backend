package com.project.tracker.resource;

import com.project.tracker.entity.User;
import lombok.Data;

@Data
public class UserResource {
    private long id;

    private String name;

    private String username;

    private String email;

    public UserResource(User user) {
    }
}

