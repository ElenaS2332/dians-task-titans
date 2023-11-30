package com.example.demo.model;

import java.util.List;

public class Wineries {
    String name;
    String region;
    String address;
    List<Wine> wineList;

    public Wineries(String name, String region, String address, List<Wine> wineList) {
        this.name = name;
        this.region = region;
        this.address = address;
        this.wineList = wineList;
    }
}
