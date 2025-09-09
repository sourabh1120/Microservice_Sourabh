package com.hotel.hotelService.service;

import com.hotel.hotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel createHotel(Hotel hotel);
    public List<Hotel> getALlHotel();
    public Hotel getHotelById(String hotelId);
}
