package com.mdp.user.service.UserService.services.impl;

import com.mdp.user.service.UserService.entities.Hotel;
import com.mdp.user.service.UserService.entities.Rating;
import com.mdp.user.service.UserService.entities.User;
import com.mdp.user.service.UserService.exceptions.ResourceNotFoundException;
import com.mdp.user.service.UserService.externalservices.HotelService;
import com.mdp.user.service.UserService.repositories.UserRepository;
import com.mdp.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found on server !! :" + userId));
        Rating[] ratingsListResponse = restTemplate.getForObject("http://RATINGS/ratings/users/" + userId, Rating[].class);
        List<Rating> ratingsList = List.of(Objects.requireNonNull(ratingsListResponse));
        ratingsList.forEach(
                rating -> {
//                    ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
//                    Hotel hotel = hotelResponseEntity.getBody();
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotel);
                }
        );
        user.setRatings(ratingsList);
        logger.info("fetched rating {}", ratingsList);
        return user;
    }
}
