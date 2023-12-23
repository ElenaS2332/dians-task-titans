package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "winery_id", nullable = false)
    @PrimaryKeyJoinColumn
    private Wineries winery;

    public Review(Long id, String comment,int score) {
        this.id = id;
        this.comment = comment;
        this.score = score;
    }

    public Review(int score, String comment, Wineries winery) {
        this.score = score;
        this.comment = comment;
        this.winery = winery;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
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
