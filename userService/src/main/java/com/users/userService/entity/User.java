package com.users.userService.entity;

import com.users.userService.dto.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="micro_users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="NAME", length = 20)
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ABOUT")
    private String about;

    @Transient
    private List<Rating> rating = new ArrayList<>();

}
