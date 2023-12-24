package com.example.demo.web.controller;

import com.example.demo.model.Review;
import com.example.demo.model.Wineries;
import com.example.demo.repository.ReviewRepositoryJPA;
import com.example.demo.service.ReviewService;
import com.example.demo.service.WineriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private List<Wineries> wineries;

    private final ReviewService reviewService;
    private final WineriesService wineriesService;

    public ReviewController(ReviewService reviewService, WineriesService wineriesService) {
        this.reviewService = reviewService;
        this.wineriesService = wineriesService;
    }
    @Autowired
    private ReviewRepositoryJPA reviewRepository;

    @GetMapping
    public String getWineryReviews(@RequestParam Long id,
                                   @RequestParam int score,
                                   @RequestParam String comment,
                                   Model model) {
        Wineries winery = wineriesService.findById(id).orElse(null);
        List<Review> reviews = this.reviewService.getReviewsByWineryId((id));
        model.addAttribute("id", id);
        model.addAttribute("score", score);
        model.addAttribute("comment", comment);

        return "review";

    }

    @PostMapping("/review")
    public String submitReview(@RequestParam Long id, @RequestParam int score, @RequestParam String comment ) {

        Optional<Wineries> winery = wineriesService.findById(id);
        // Create a new review
        Review review = new Review();
        //review.setId(id);
        review.setComment(comment);
        review.setScore(score);

        reviewRepository.save(review);

        winery.ifPresent(review::setWinery);

        ReviewService.addReview(review);

        return "redirect:/review";
    }
}
