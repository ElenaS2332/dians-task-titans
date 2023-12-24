package com.example.demo.service.impl;

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
        return reviewRepository.findByWineryId(ID);
    }


    @Transactional
    public Review save(int score, String comment, Long ID) {
        Optional<Wineries> winery = this.wineriesRepository.findById(ID);


        return this.reviewRepository.save(new Review(score,comment,winery.get()));
    }
}
