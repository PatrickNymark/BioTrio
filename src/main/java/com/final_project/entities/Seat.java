package com.final_project.entities;

public class Seat {
    private int id;
    private boolean isReserved;
    private int row;
    private int nr;
    private int theaterId;

    public Seat() {
    }

    public Seat(int id, boolean isReserved, int row, int nr, int theaterId) {
        this.id = id;
        this.isReserved = isReserved;
        this.row = row;
        this.nr = nr;
        this.theaterId = theaterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

}
