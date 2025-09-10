package com.users.userService.service.impl;

import com.users.userService.dto.Hotel;
import com.users.userService.dto.Rating;
import com.users.userService.entity.User;
import com.users.userService.exception.ResourceNotFoundException;
import com.users.userService.externalService.HotelService;
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

    private HotelService hotelService;

    private Logger logger=  LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService){
        this.userRepository=userRepository;
        this.restTemplate=restTemplate;
        this.hotelService=hotelService;
    }

    @Override
    public User createUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }


    @Override
    public List<User> getALlUser() {
        // Get all users
        List<User> users = userRepository.findAll();

        // Set ratings and hotels for each user
        for (User user : users) {
            try {
                // Get ratings as array for each user
                Rating[] ratingsArray = restTemplate.getForObject(
                        "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                        Rating[].class
                );

                if (ratingsArray != null && ratingsArray.length > 0) {
                    List<Rating> ratingsWithHotels = Arrays.stream(ratingsArray)
                            .map(rating -> {
                                if (rating.getHotelId() != null && !rating.getHotelId().trim().isEmpty()) {
                                    try {
                                        // Fetch hotel details for each rating
                                        Hotel hotel = restTemplate.getForObject(
                                                "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
                                                Hotel.class
                                        );
                                        rating.setHotel(hotel);
                                    } catch (Exception e) {
                                        System.err.println("Error fetching hotel for user " + user.getUserId() +
                                                ", hotelId: " + rating.getHotelId() + " - " + e.getMessage());
                                        rating.setHotel(null);
                                    }
                                }
                                return rating;
                            })
                            .collect(Collectors.toList());

                    user.setRating(new ArrayList<>(ratingsWithHotels));
                } else {
                    user.setRating(new ArrayList<>());
                }

            } catch (Exception e) {
                System.err.println("Error fetching ratings for user: " + user.getUserId() + " - " + e.getMessage());
                user.setRating(new ArrayList<>());
            }
        }

        return users;
    }


 /**   @Override
    public User getUser(String userId) {

        //
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found with server!!" + userId));


        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);

        user.setRating(ratingOfUser);
        return user;
    }
*/

 @Override
 public User getUser(String userId) {
     // Get user by ID
     User user = userRepository
             .findById(userId)
             .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found with server!!" + userId));

     try {
         // Get ratings as array first, then convert to list
         Rating[] ratingsArray = restTemplate.getForObject(
                 "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                 Rating[].class
         );

         if (ratingsArray != null && ratingsArray.length > 0) {
             List<Rating> ratingsWithHotels = Arrays.stream(ratingsArray)
                     .map(rating -> {
                         try {
                          /**
                           * //RestTemplate
                           * Hotel hotel = restTemplate.getForObject(
                                     "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
                                     Hotel.class
                             );*/
                          //Feign CLient here
                          Hotel hotel=hotelService.getHOtel(rating.getHotelId());
                             rating.setHotel(hotel);
                         } catch (Exception e) {
                             System.err.println("Error fetching hotel for hotelId: " + rating.getHotelId());
                             rating.setHotel(null);
                         }
                         return rating;
                     })
                     .collect(Collectors.toList());

             user.setRating(new ArrayList<>(ratingsWithHotels));
         } else {
             user.setRating(new ArrayList<>());
         }

     } catch (Exception e) {
         System.err.println("Error fetching ratings for userId: " + userId);
         user.setRating(new ArrayList<>());
     }

     return user;
 }
}
