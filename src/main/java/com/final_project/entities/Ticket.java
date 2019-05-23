package com.final_project.entities;

public class Ticket {
    private int id;
    private String bookingCode;
    private int seatNr;
    private int seatRow;
    private int moviePlayId;

    public Ticket() {
    }

    public Ticket(int id, String bookingCode, int seatNr, int seatRow, int moviePlayId) {
        this.id = id;
        this.bookingCode = bookingCode;
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

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
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
