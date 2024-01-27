package com.example.wineriesserviceapi.controller;

import com.example.wineriesserviceapi.model.Review;
import com.example.wineriesserviceapi.model.Wineries;
import com.example.wineriesserviceapi.service.ReviewService;
import com.example.wineriesserviceapi.service.WineriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final WineriesService wineriesService;

    public ReviewController(ReviewService reviewService, WineriesService wineriesService) {
        this.reviewService = reviewService;
        this.wineriesService = wineriesService;
    }

    @GetMapping("/review/{id}")
    public String getWineryReviews(@PathVariable Long id,
                                   @RequestParam float score,
                                   @RequestParam String comment,
                                   Model model){
        Wineries winery = wineriesService.findById(id);
        Review review=new Review();
        review.setScore(score);
        review.setComment(comment);
        List<Review> reviews = this.reviewService.getReviewsByWineryId((id));
        winery.setReviews(reviews);
        model.addAttribute("id", id);
        model.addAttribute("winery",winery);
        model.addAttribute("review",review);
        return "review";
    }

    @PostMapping("/saveReview/{id}")
    public String submitReview(@PathVariable Long id,
                                     @RequestParam float score,
                                     @RequestParam String comment) {
        reviewService.save(id,score,comment);
        return "review";
    }
}
