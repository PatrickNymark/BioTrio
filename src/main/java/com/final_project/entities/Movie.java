package com.final_project.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Entity
public class Movie {


    @Id @GeneratedValue
    private int id ;
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @NotEmpty(message = "Genre can not be empty")
    private String genre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseYear;
    private double rating;
    private int ageLimit;
    @Min(value = 1, message = "Length can not be 0")
    private int lengthInMinutes;
    private String imageName;
    private String trailerUrl;

    public Movie() {
    }

    public Movie(@NotEmpty(message = "Title can not be empty") String title, @NotEmpty(message = "Genre can not be empty") String genre, LocalDate releaseYear, double rating, int ageLimit, @Min(value = 1, message = "Length can not be 0") int lengthInMinutes, String imageName, String trailerUrl) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.ageLimit = ageLimit;
        this.lengthInMinutes = lengthInMinutes;
        this.imageName = imageName;
        this.trailerUrl = trailerUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                ", lengthInMinutes=" + lengthInMinutes +
                ", imageName='" + imageName + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }
}

