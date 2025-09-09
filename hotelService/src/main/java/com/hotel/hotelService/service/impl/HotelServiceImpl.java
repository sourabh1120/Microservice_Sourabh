package com.hotel.hotelService.service.impl;

import com.hotel.hotelService.Repository.HotelRepository;
import com.hotel.hotelService.entity.Hotel;
import com.hotel.hotelService.exception.ResourceNotFoundException;
import com.hotel.hotelService.service.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {

        String randomHotelId = UUID.randomUUID().toString();
            hotel.setId(randomHotelId);

        Hotel savedHotel = hotelRepository.save(hotel);
        return savedHotel;
    }

    @Override
    public List<Hotel> getALlHotel() {
        List<Hotel> allHotel = hotelRepository.findAll();
        return allHotel;
    }

    @Override
    public Hotel getHotelById(String hotelId) {

        return hotelRepository
                .findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("hotel WIth given id not found !!" +hotelId ));
    }
}
