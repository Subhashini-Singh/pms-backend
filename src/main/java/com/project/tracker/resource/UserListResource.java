package com.project.tracker.resource;

import java.util.List;

public class UserListResource {
    private List<UserResource> userList;

    public List<UserResource> getUserList() {
        return userList;
    }

    public void setUserList(List<UserResource> userList) {
        this.userList = userList;
    }
}
