package com.example.demo.repository;

import com.example.demo.model.Wineries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineriesRepository extends JpaRepository<Wineries,Long> {

}
