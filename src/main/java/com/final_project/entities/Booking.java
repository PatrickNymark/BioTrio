package com.final_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Booking {
    private int id;
    private int moviePlayId;
    private List<Ticket> tickets;
    private int totalPrice;

    public Booking() {
    }

    public Booking(int id, int moviePlayId, List<Ticket> tickets, int totalPrice) {
        this.id = id;
        this.moviePlayId = moviePlayId;
        this.tickets = tickets;
        this.totalPrice = totalPrice;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

