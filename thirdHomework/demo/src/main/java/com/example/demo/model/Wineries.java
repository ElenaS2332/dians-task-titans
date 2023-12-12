package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wineries {
    @JsonProperty("id")
    public long ID;

    public Double latitude;
    public Double longitude;

    public String name;

    public String location;

    @JsonProperty("img")
    public String image;

    // Default constructor
    public Wineries() {
    }

    public Wineries(@JsonProperty("id") long ID,
                    @JsonProperty("latitude") Double latitude,
                    @JsonProperty("longitude") Double longitude,
                    @JsonProperty("name") String name,
                    @JsonProperty("img") String image,
                    @JsonProperty("location") String location) {
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
