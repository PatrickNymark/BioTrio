package com.final_project.entities;

public class Ticket {
    private int id;
    private int bookingId;
    private int seatNr;
    private int seatRow;
    private int moviePlayId;

    public Ticket() {
    }

    public Ticket(int id, int bookingId, int seatNr, int seatRow, int moviePlayId) {
        this.id = id;
        this.bookingId = bookingId;
        this.seatNr = seatNr;
        this.seatRow = seatRow;
        this.moviePlayId = moviePlayId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getSeatNr() {
        return seatNr;
    }

    public void setSeatNr(int seatNr) {
        this.seatNr = seatNr;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getMoviePlayId() {
        return moviePlayId;
    }

    public void setMoviePlayId(int moviePlayId) {
        this.moviePlayId = moviePlayId;
    }
}
