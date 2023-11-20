/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Flight {
    private int flightID;
    private String flightName;
    private Admin createBy;
    private Timestamp createDate;
    private Admin updateBy;
    private Timestamp upgradeDate;
    private Aircraft aircraftID;
    private String arrivalCity;
    private Timestamp arrivalTime;
    private String departureCity;
    private Timestamp departureTime;
    private int availableTicket;
    private float price;

   
    

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableTicket() {
        return availableTicket;
    }

    public void setAvailableTicket(int availableTicket) {
        this.availableTicket = availableTicket;
    }

    public Flight() {
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String fligghtName) {
        this.flightName = fligghtName;
    }

    

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Admin getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Admin createBy) {
        this.createBy = createBy;
    }

    public Admin getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Admin updateBy) {
        this.updateBy = updateBy;
    }

    

    public Timestamp getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(Timestamp upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public Aircraft getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(Aircraft aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }
    
    
}
