package com.users.userService.service;

import com.users.userService.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public List<User> getALlUser();
    public User getUser(String userId);
}
