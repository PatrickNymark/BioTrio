package com.final_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking {

    @Id @GeneratedValue
    private int id;
    private int moviePlayId;
    private int seatRow;
    private int seatNr;

    public Booking() {
    }

    public Booking(int moviePlayId, int seatRow, int seatNr) {
        this.moviePlayId = moviePlayId;
        this.seatRow = seatRow;
        this.seatNr = seatNr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoviePlayId() {
        return moviePlayId;
    }

    public void setMoviePlayId(int moviePlayId) {
        this.moviePlayId = moviePlayId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNr() {
        return seatNr;
    }

    public void setSeatNr(int seatNr) {
        this.seatNr = seatNr;
    }
}

