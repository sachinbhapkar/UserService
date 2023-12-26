package com.mdp.user.service.UserService.services.impl;

import com.mdp.user.service.UserService.exceptions.ResourceNotFoundException;
import com.mdp.user.service.UserService.entities.User;
import com.mdp.user.service.UserService.repositories.UserRepository;
import com.mdp.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id not found on server !! :"+ userId));
    }
}
