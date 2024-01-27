package com.example.wineriesserviceapi.repository;

import com.example.wineriesserviceapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositoryJPA extends JpaRepository<Review,Long> {
    List<Review> findByWineryId(Long ID);
}
