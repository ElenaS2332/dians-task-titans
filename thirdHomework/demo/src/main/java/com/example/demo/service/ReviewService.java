package com.example.demo.service;

import com.example.demo.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    static void addReview(Review review) {}
    List<Review> getReviewsByWineryId(Long ID);
}
