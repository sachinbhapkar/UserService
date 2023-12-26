package com.mdp.user.service.UserService.services;

import com.mdp.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //User operations

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    //TODO

    //delete

    //update
}
