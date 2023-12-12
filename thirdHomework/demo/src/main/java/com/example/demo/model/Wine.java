package com.example.demo.model;

import lombok.Data;

@Data
public class Wine {
    String name;
    String sort;
    int year;
    String producedInRegion;

    public Wine(String name, String sort, int year) {
        this.name = name;
        this.sort = sort;
        this.year = year;
        this.producedInRegion = producedInRegion;
    }
}
