/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Admin {
    private int adminID;
    private String adminUsername, adminPassword, adminName, adminEmail, adminPhone;
    private boolean adminGender;
    private Date adminDOB;
    private String adminCCCD, adminAddress;

    public Admin() {
    }

    public Admin(int adminID, String adminUsername, String adminPassword, String adminName, String adminEmail, String adminPhone, boolean adminGender, Date adminDOB, String adminAddress, String adminCCCD) {
        this.adminID = adminID;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
        this.adminGender = adminGender;
        this.adminDOB = adminDOB;
        this.adminAddress = adminAddress;
        this.adminCCCD = adminCCCD;
        
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public boolean isAdminGender() {
        return adminGender;
    }

    public void setAdminGender(boolean adminGender) {
        this.adminGender = adminGender;
    }

    public Date getAdminDOB() {
        return adminDOB;
    }

    public void setAdminDOB(Date adminDOB) {
        this.adminDOB = adminDOB;
    }
    
    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminCCCD() {
        return adminCCCD;
    }

    public void setAdminCCCD(String adminCCCD) {
        this.adminCCCD = adminCCCD;
    }
    
}
