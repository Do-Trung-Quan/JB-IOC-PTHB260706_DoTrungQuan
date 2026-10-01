package model;

import java.sql.Timestamp;

public class TicketBooking {
    private int bookingId;
    private String movieTitle;
    private String customerName;
    private Timestamp showTime;
    private Timestamp bookingDate;
    private int seatQuantity;
    private String status;

    public TicketBooking() {}

    public TicketBooking(int bookingId, String movieTitle, String customerName, Timestamp showTime, Timestamp bookingDate, int seatQuantity, String status) {
        this.bookingId = bookingId;
        this.movieTitle = movieTitle;
        this.customerName = customerName;
        this.showTime = showTime;
        this.bookingDate = bookingDate;
        this.seatQuantity = seatQuantity;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getShowTime() {
        return showTime;
    }
    public void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getSeatQuantity() {
        return seatQuantity;
    }
    public void setSeatQuantity(int seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return String.format("| %-10d | %-20s | %-15s | %-20s | %-20s | %-12d | %-12s |", bookingId, movieTitle, customerName, showTime, bookingDate, seatQuantity, status);
    }
}
