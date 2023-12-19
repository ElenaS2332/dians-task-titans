package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "wineries")
public class Wineries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public long id;

    public Double latitude;
    public Double longitude;

    public String name;

    public String location;
    public String rating;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    @JsonProperty("img")
    public String image;

    // Default constructor
    public Wineries() {
    }

    public Wineries(@JsonProperty("id") long id,
                    @JsonProperty("latitude") Double latitude,
                    @JsonProperty("longitude") Double longitude,
                    @JsonProperty("name") String name,
                    @JsonProperty("img") String image,
                    @JsonProperty("location") String location,
                    @JsonProperty("rating") String rating){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.location = location;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public long getId() {
        return id;
    }


    public String getLocation() {
        return location;
    }
}
