package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositoryJPA extends JpaRepository<Review,Long> {
    List<Review> findByWineryId(Long ID);
}
