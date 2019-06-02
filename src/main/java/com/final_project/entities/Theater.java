package com.final_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Theater {

    @Id @GeneratedValue
    private int id;
    private String theaterName;
    private int seatsPerRow;
    private int numberOfRows;

    public Theater() {

    }

    public Theater(int id, String theaterName, int seatsPerRow, int numberOfRows) {
        this.id=id;
        this.theaterName=theaterName;
        this.seatsPerRow=seatsPerRow;
        this.numberOfRows=numberOfRows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
}
