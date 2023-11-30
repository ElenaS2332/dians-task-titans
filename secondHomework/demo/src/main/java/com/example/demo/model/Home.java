package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Home {

    List<Wineries> wineriesList;

    public Home(List<Wineries> wineriesList) {
        this.wineriesList = wineriesList;
    }
}
