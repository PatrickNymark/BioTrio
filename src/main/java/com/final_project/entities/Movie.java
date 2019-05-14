package com.final_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Movie {


    @Id @GeneratedValue
    private int id ;

    private String title;
    private String genre;
    private LocalDate releaseYear;
    private double rating;
    private int ageLimit;

    public Movie() {
    }

    public Movie(int id, String title, String genre, LocalDate releaseYear, double rating, int ageLimit) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.ageLimit = ageLimit;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}

