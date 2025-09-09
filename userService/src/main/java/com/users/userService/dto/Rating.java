package com.users.userService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String feedback;
}
