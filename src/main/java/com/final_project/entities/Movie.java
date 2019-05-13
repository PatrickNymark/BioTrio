package com.final_project.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private Date releaseYear;
    private double rating;
    private int ageLimit;

    public Movie(){}

    public Movie(int id,String title,String genre,Date releaseYear,double rating,int ageLimit){
        this.id=id;
        this.title=title;
        this.genre=genre;
        this.releaseYear=releaseYear;
        this.rating=rating;
        this.ageLimit=ageLimit;
    }

    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getGenre(){return genre;}
    public Date getReleaseYear(){return releaseYear;}
    public double getRating(){return rating;}
    public int getAgeLimit(){return ageLimit; }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
