package com.example.wineriesservices;

import java.util.List;

public interface WineriesService {
    void saveWineriesData(List<Wineries> wineries);
    Wineries findById(Long ID);
}
