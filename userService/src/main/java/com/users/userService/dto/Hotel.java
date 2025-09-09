package com.users.userService.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Hotel {

    private  String id;
    private  String name;
    private  String location;
    private  String about;
}
