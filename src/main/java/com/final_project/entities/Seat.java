package com.final_project.entities;

public class Seat {
    private int id;
    private boolean isReserved;
    private String seatNumber;

    public Seat() {
    }

    public Seat(int id, boolean isReserved, String seatNumber) {
        this.id = id;
        this.isReserved = isReserved;
        this.seatNumber = seatNumber;
    }
}
