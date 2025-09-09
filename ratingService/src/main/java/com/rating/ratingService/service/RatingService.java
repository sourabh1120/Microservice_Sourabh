package com.rating.ratingService.service;

import com.rating.ratingService.Entity.Rating;
import com.rating.ratingService.repository.RatingRepository;

import java.util.List;

public interface RatingService {


    public Rating createRating(Rating rating);
    public List<Rating> getAllRating();
    public List<Rating> getRatingByUserId(String userId);
    public List<Rating> getRatingByHotelId(String hotelId);
}
