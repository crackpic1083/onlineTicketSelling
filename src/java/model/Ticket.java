/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Ticket {
    private int ticketID;
    private String ticketName;
    private float price;
    private Flight flightID;
    private int buyer;
    private String seatName;

    public Ticket() {
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Flight getFlightID() {
        return flightID;
    }

    public void setFlightID(Flight flightID) {
        this.flightID = flightID;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }
    
    
    
}
