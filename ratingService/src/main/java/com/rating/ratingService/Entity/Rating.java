package com.rating.ratingService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "micro_rating")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
public class Rating {

    @Id
    private String ratingID;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
