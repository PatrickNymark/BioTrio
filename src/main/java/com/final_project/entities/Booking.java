package com.final_project.entities;

import javax.validation.constraints.Size;
import java.util.List;

public class Booking {
    private int moviePlayId;
    private List<Ticket> tickets;
    private int totalPrice;
    private String bookingCode;

    public Booking() {

    }

    public Booking(int moviePlayId, List<Ticket> tickets, int totalPrice, String bookingCode) {
        this.moviePlayId = moviePlayId;
        this.tickets = tickets;
        this.totalPrice = totalPrice;
        this.bookingCode = bookingCode;
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

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    /**
     * Method to generate random booking code
     *
     * @return String
     */
    public static String generateBookingCode() {
        int n = 20;

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

    @Override
    public String toString() {
        return "Booking{" +
                "moviePlayId=" + moviePlayId +
                ", tickets=" + tickets +
                ", totalPrice=" + totalPrice +
                ", bookingCode='" + bookingCode + '\'' +
                '}';
    }
}

