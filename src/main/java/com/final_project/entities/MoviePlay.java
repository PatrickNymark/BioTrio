package com.final_project.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class MoviePlay {

    @Id @GeneratedValue
    private int id;
    private int movieId;
    private int theaterId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime playStart;

    public MoviePlay() {
    }

    public MoviePlay(int movieId, int theaterId, LocalDateTime playStart) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.playStart = playStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public LocalDateTime getPlayStart() {
        return playStart;
    }

    public void setPlayStart(LocalDateTime playStart) {
        this.playStart = playStart;
    }

    @Override
    public String toString() {
        return "MoviePlay{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", theaterId=" + theaterId +
                ", playStart=" + playStart +
                '}';
    }
}
