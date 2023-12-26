package com.mdp.user.service.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rating {

    private String userId;
    private String ratingId;
    private String hotelId;
    private int rating;
    private String feedback;


}
