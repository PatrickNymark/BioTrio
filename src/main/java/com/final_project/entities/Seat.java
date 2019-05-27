package com.final_project.entities;

public class Seat {
    private int row;
    private int nr;
    private boolean isReserved;

    public Seat() {
    }

    public Seat(int row, int nr, boolean isReserved) {
        this.row = row;
        this.nr = nr;
        this.isReserved = isReserved;
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

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
