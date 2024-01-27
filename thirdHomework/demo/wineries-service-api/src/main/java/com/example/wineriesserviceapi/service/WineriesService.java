package com.example.wineriesserviceapi.service;

import com.example.wineriesserviceapi.model.Wineries;

import java.util.List;

public interface WineriesService {
    void saveWineriesData(List<Wineries> wineries);
    Wineries findById(Long ID);
}
