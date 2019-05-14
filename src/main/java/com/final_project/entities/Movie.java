package com.final_project.entities;

<<<<<<< HEAD
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
=======
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
>>>>>>> fa787d91b843f04dc2ae362584a9f6da784c20b0

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

<<<<<<< HEAD
    public void setReleaseYear(LocalDate releaseYear) {
=======
    public void setReleaseYear(Date releaseYear) {
>>>>>>> fa787d91b843f04dc2ae362584a9f6da784c20b0
        this.releaseYear = releaseYear;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
<<<<<<< HEAD

=======
>>>>>>> fa787d91b843f04dc2ae362584a9f6da784c20b0
