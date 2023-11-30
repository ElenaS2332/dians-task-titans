package com.example.demo.service.impl;

import com.example.demo.repository.WineRepository;
import com.example.demo.service.WineService;
import org.springframework.stereotype.Service;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }
}
