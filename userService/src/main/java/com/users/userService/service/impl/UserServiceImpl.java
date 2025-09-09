package com.users.userService.service.impl;

import com.users.userService.entity.User;
import com.users.userService.exception.ResourceNotFoundException;
import com.users.userService.repository.UserRepository;
import com.users.userService.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User createUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getALlUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User with given id is not found with server!!" +userId));
    }
}
