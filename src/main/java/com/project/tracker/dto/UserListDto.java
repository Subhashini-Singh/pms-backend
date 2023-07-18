package com.project.tracker.dto;

import java.util.List;

public class UserListDto {
    private List<UserDto> userList;

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }
}
