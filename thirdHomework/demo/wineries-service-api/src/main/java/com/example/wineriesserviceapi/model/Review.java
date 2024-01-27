package com.example.wineriesserviceapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float score;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Wineries winery;

    public Review(Long id, float score, String comment) {
        this.id = id;
        this.comment = comment;
        this.score = score;
    }

    public Review(float score, String comment) {
        this.score = score;
        this.comment = comment;

    }

    public Review(float score, String comment, Wineries winery) {

        this.score = score;
        this.comment = comment;
        this.winery = winery;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Wineries getWinery() {
        return winery;
    }

    public Review() {
    }

}
