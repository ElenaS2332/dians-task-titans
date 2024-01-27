package com.example.wineriesserviceapi.service;

import com.example.wineriesserviceapi.model.Review;

import java.util.List;


public interface ReviewService {
    //Review addReview(Integer score, String comment);
    List<Review> getReviewsByWineryId(Long ID);

    Review save(Long id,float score, String comment);

}
