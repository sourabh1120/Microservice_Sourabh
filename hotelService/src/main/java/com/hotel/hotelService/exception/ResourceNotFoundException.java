package com.hotel.hotelService.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not Found On Server !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
