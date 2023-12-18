package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wineries")
public class Wineries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public long ID;

    public Double latitude;
    public Double longitude;

    public String name;

    public String location;
    public String rating;
    public String comment;

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
                    @JsonProperty("location") String location,
                    @JsonProperty("rating") String rating,
                    @JsonProperty("comment") String comment){
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.location = location;
        this.rating=rating;
        this.comment=comment;
    }

    public String getLocation() {
        return location;
    }
}
