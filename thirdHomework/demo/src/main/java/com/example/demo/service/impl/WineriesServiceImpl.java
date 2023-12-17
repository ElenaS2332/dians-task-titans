package com.example.demo.service.impl;

import com.example.demo.model.Wineries;
import com.example.demo.repository.WineriesRepository;
import com.example.demo.service.WineriesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineriesServiceImpl implements WineriesService {
    @Autowired
    private final WineriesRepository wineriesRepository;

    public WineriesServiceImpl(WineriesRepository wineriesRepository) {
        this.wineriesRepository = wineriesRepository;
    }

    @Transactional
    public void saveWineriesData(List<Wineries> wineries) {
        wineriesRepository.saveAll(wineries);
    }

}
