package com.example.wineriesserviceapi.repository;

import com.example.wineriesserviceapi.model.Wineries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineriesRepository extends JpaRepository<Wineries,Long> {


}
