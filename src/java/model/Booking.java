/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Booking {
    private int bookingID;
    private User customerID;
    private Timestamp bookingDate;
    private Admin adminID;
    private int status;
    private Ticket tid;
    private String phone;
    private String name;
    private String email;
    private Flight fid;
    private Aircraft aid;
    
    public Ticket getTid() {
        return tid;
    }

    public void setTid(Ticket tid) {
        this.tid = tid;
    }
    
    public Flight getFid() {
        return fid;
    }

    public void setFid(Flight fid) {
        this.fid = fid;
    }

    public Aircraft getAid() {
        return aid;
    }

    public void setAid(Aircraft aid) {
        this.aid = aid;
    }    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public Booking() {
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public User getCustomerID() {
        return customerID;
    }

    public void setCustomerID(User customerID) {
        this.customerID = customerID;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
    
    
}
