package com.example.demo.service.impl;

import com.example.demo.model.InvalidArgumentsException;
import com.example.demo.model.Review;
import com.example.demo.model.Wineries;
import com.example.demo.repository.InMemoryWineriesRepository;
import com.example.demo.repository.ReviewRepositoryJPA;
import com.example.demo.repository.WineriesRepository;
import com.example.demo.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
