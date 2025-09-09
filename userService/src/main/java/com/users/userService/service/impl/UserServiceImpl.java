package com.users.userService.service.impl;

import com.users.userService.dto.Hotel;
import com.users.userService.dto.Rating;
import com.users.userService.entity.User;
import com.users.userService.exception.ResourceNotFoundException;
import com.users.userService.repository.UserRepository;
import com.users.userService.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private RestTemplate restTemplate;

    private Logger logger=  LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate){
        this.userRepository=userRepository;
        this.restTemplate=restTemplate;
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

        //
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found with server!!" + userId));


        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);

        user.setRating(ratingOfUser);
        return user;
    }

}
