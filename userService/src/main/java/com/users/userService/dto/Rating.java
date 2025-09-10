package com.users.userService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @JsonProperty("ratingID")  // Maps to "ratingID" in JSON
    private String ratingId;

   // @JsonProperty("userId")    // Maps to "userId" in JSON
    private String userId;

   // @JsonProperty("hotelId")   // Maps to "hotelId" in JSON (lowercase 'd')
    private String hotelId;

    //@JsonProperty("rating")
    private int rating;

  //  @JsonProperty("feedback")
    private String feedback;

    private Hotel hotel;
}
