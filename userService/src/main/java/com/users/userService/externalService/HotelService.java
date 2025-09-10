package com.users.userService.externalService;

import com.users.userService.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHOtel(@PathVariable("hotelId") String hotelId);
}
