package com.example.DiningReview.controller;

import com.example.DiningReview.model.DiningReview;
import com.example.DiningReview.model.Restaurant;
import com.example.DiningReview.model.ReviewStatus;
import com.example.DiningReview.model.User;
import com.example.DiningReview.repository.DiningReviewRepository;
import com.example.DiningReview.repository.RestaurantRepository;
import com.example.DiningReview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class DiningReviewController {

    private final UserRepository userRepository;
    private final DiningReviewRepository diningReviewRepository;

    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(UserRepository userRepository, DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/reviews")
    public ResponseEntity<DiningReview> addReview(@RequestBody DiningReview diningReview){
        Optional<User> reviewerOptional = Optional.ofNullable(this.userRepository.findByUsername(diningReview.getWhoSubmitted()));
        if(!reviewerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(this.userRepository.existsByUsername(reviewerOptional.get().getUsername())){
            Restaurant restaurant = this.restaurantRepository.findByName(diningReview.getRestaurant());
            restaurant.setEggScore(diningReview.getEggScore());
            restaurant.setPeanutScore(diningReview.getPeanutScore());
            restaurant.setDairyScore(diningReview.getDairyScore());
            restaurant.setTotalScore(((double)diningReview.getDairyScore() + (double)diningReview.getEggScore() + (double)diningReview.getPeanutScore())/3);
            this.restaurantRepository.save(restaurant);
            return new ResponseEntity<>(this.diningReviewRepository.save(diningReview), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping ("/admin/changereviewstatus")
    public ResponseEntity<DiningReview> changeReviewStatus(@RequestBody DiningReview diningReview, String status){
        if(status.equals("accept")){
            diningReview.setReviewStatus(ReviewStatus.ACCEPTED);
        } else if (status.equals("reject")){
            diningReview.setReviewStatus(ReviewStatus.REJECTED);
        } else if(status.equals("in_review")){
            diningReview.setReviewStatus(ReviewStatus.IN_REVIEW);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.diningReviewRepository.save(diningReview);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
