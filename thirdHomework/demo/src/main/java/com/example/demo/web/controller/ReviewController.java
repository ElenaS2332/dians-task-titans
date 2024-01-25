package com.example.demo.web.controller;

import com.example.demo.logger.WineryLogger;
import com.example.demo.model.Review;
import com.example.demo.model.Wineries;
import com.example.demo.repository.ReviewRepositoryJPA;
import com.example.demo.service.ReviewService;
import com.example.demo.service.WineriesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final WineriesService wineriesService;
    private final WineryLogger wineryLogger = WineryLogger.getInstance();

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
