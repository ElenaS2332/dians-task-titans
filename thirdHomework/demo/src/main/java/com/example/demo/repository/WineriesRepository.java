package com.example.demo.repository;

import com.example.demo.model.Wine;
import com.example.demo.model.Wineries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineriesRepository extends JpaRepository<Wineries,Long> {


}
