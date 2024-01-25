package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "wineries")
public class Wineries {
    @Id
    @JsonProperty("id")
    public long id;

    public Double latitude;
    public Double longitude;

    public String name;

    public String location;
    public String rating;
    public String description;
    public String phone;

    @OneToMany(mappedBy = "winery")
    private List<Review> reviews;
    @JsonProperty("img")
    public String image;

    public Wineries() {
    }

    public Wineries(@JsonProperty("id") long id,
                    @JsonProperty("latitude") Double latitude,
                    @JsonProperty("longitude") Double longitude,
                    @JsonProperty("name") String name,
                    @JsonProperty("img") String image,
                    @JsonProperty("location") String location,
                    @JsonProperty("rating") String rating,
                    @JsonProperty("description")String description,
                    @JsonProperty("phone")String phone){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.location = location;
        this.rating=rating;
        this.description=description;
        this.phone = phone;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        double averageRating = reviews.stream()
                .mapToDouble(Review::getScore)
                .average()
                .orElse(0.0);

        return String.format("%.2f", averageRating);
    }


    public long getId() {
        return id;
    }

    public void addReview(Review review)
    {
        this.reviews.add(review);
    }

    public String getLocation() {
        return location;
    }
}
