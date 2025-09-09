package com.rating.ratingService.service.impl;

import com.rating.ratingService.Entity.Rating;
import com.rating.ratingService.repository.RatingRepository;
import com.rating.ratingService.service.RatingService;

import java.util.List;
import java.util.UUID;

public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }

    @Override
    public Rating createRating(Rating rating) {
        String randomRatingId =UUID.randomUUID().toString();
        rating.setRatingID(randomRatingId);
        return rating;
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
