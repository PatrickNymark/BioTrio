package com.final_project.entities;

public class Theater {
        private int id;
        private String theaterName;
        private int seatPerRow;
        private int rowNumber;

        public Theater() {

        }

    public Theater(int id, String theaterName, int seatPerRow, int rowNumber) {
        this.id=id;
        this.theaterName=theaterName;
        this.seatPerRow=seatPerRow;
        this.rowNumber=rowNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName=theaterName;
    }

    public int getSeatPerRow() {
        return seatPerRow;
    }

    public void setSeatPerRow(int seatPerRow) {
        this.seatPerRow=seatPerRow;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber=rowNumber;
    }
}
