package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.model.Wineries;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {
    List<Review> getReviewsByWineryId(Long ID);

    Review save(Long id,float score, String comment);

}
