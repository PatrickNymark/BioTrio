package com.final_project.entities;

public class Ticket {
    private int id;
    private int bookingId;
    private String seatNumber;

    public Ticket() {
    }

    public Ticket(int id, int bookingId, String seatNumber) {
        this.id = id;
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
