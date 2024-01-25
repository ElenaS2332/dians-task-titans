package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.model.Wineries;

import java.util.List;
import java.util.Optional;

public interface WineriesService {
    void saveWineriesData(List<Wineries> wineries);
    Wineries findById(Long ID);
    void setWineryRating(Review review, Long wineryId);
}
