/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User extends DBContext {

    private int customerID;
    private String customerUsername, customerPassword, customerName, customerEmail, customerPhone, customerAddress, customerCCCD;
    private boolean customerGender;
    private Date customerDOB;

    public User() {
    }

    public User(int customerID, String customerUsername, String customerPassword, String customerName, String customerEmail, String customerPhone, Date customerDOB, boolean customerGender, String customerAdress, String customerCCCD) {
        this.customerID = customerID;
        this.customerUsername = customerUsername;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerDOB = customerDOB;
        this.customerGender = customerGender;
        this.customerAddress = customerAdress;
        this.customerCCCD = customerCCCD;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(Date customerDOB) {
        this.customerDOB = customerDOB;
    }

    public boolean getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(boolean customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAdress) {
        this.customerAddress = customerAdress;
    }

    public String getCustomerCCCD() {
        return customerCCCD;
    }

    public void setCustomerCCCD(String customerCCCD) {
        this.customerCCCD = customerCCCD;
    }


}
