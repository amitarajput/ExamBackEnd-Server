package com.exam.service;

import com.exam.entities.User;
import com.exam.entities.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by id

    public void deleleUser(Long userId);
}
