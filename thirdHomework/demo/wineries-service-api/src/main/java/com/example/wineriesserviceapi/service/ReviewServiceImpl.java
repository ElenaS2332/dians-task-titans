package com.example.wineriesserviceapi.service;

import com.example.wineriesserviceapi.model.InvalidArgumentsException;
import com.example.wineriesserviceapi.model.Review;
import com.example.wineriesserviceapi.model.Wineries;
import com.example.wineriesserviceapi.repository.ReviewRepositoryJPA;
import com.example.wineriesserviceapi.repository.WineriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepositoryJPA reviewRepository;
    private final WineriesRepository wineriesRepository;

    public ReviewServiceImpl(ReviewRepositoryJPA reviewRepository, WineriesRepository wineriesRepository) {
        this.reviewRepository = reviewRepository;
        this.wineriesRepository = wineriesRepository;
    }

    @Override
    public List<Review> getReviewsByWineryId(Long ID) {
        List<Review> reviews=reviewRepository.findByWineryId(ID);
        return reviews;
    }

    @Override
    public Review save(Long id,float score, String comment) {
        Wineries winery=wineriesRepository.findById(id).orElseThrow(InvalidArgumentsException::new);
        Review review= new Review(score,comment,winery);
        Review savedReview=reviewRepository.save(review);
        return savedReview;
    }

}
