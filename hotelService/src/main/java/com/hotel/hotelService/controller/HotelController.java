package com.hotel.hotelService.controller;

import com.hotel.hotelService.entity.Hotel;
import com.hotel.hotelService.service.HotelService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel savedHotel = hotelService.createHotel(hotel);

        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> hotels = hotelService.getALlHotel();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }


    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHOtelById(@PathVariable String hotelId){
        Hotel hotelID = hotelService.getHotelById(hotelId);
        return  new ResponseEntity<>(hotelID,   HttpStatus.OK);
    }

}
