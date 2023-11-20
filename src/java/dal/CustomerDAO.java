/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.User;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    public User getUser(String username, String password) {
        try {
            String sql = "select * from dbo.Customer where customerUsername=? and customerPassword=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setCustomerID(rs.getInt(1));
                user.setCustomerUsername(rs.getString(2));
                user.setCustomerPassword(rs.getString(3));
                user.setCustomerName(rs.getString(4));
                user.setCustomerEmail(rs.getString(5));
                user.setCustomerPhone(rs.getString(6));
                user.setCustomerDOB(rs.getDate(7));
                user.setCustomerGender(rs.getBoolean(8));
                user.setCustomerAddress(rs.getString(9));
                user.setCustomerCCCD(rs.getString(10));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String isValidUsername(String username) {
        try {
            String strSQL = "select customerUsername from dbo.Customer where customerUsername=?";
            PreparedStatement stm = connection.prepareStatement(strSQL);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addUser(User u) {
        try {
            String sql = "INSERT INTO dbo.Customer\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getCustomerUsername());
            stm.setString(2, u.getCustomerPassword());
            stm.setString(3, u.getCustomerName());
            stm.setString(4, u.getCustomerEmail());
            stm.setString(5, u.getCustomerPhone());
            stm.setDate(6, u.getCustomerDOB());
            stm.setBoolean(7, u.getCustomerGender());
            stm.setString(8, u.getCustomerAddress());
            stm.setString(9, u.getCustomerCCCD());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean bookTicket(Booking bookingID) {
        try {
            String sql = "INSERT INTO [dbo].[Booking]\n"
                    + "           ([customerID]\n"
                    + "           ,[status]\n"
                    + "           ,[passengerPhone]\n"
                    + "           ,[passengerName]\n"
                    + "           ,[passengerEmail]\n"
                    + "           ,[ticketID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bookingID.getCustomerID().getCustomerID());
            stm.setInt(2, bookingID.getStatus());
            stm.setString(3, bookingID.getPhone());
            stm.setString(4, bookingID.getName());
            stm.setString(5, bookingID.getEmail());
            stm.setInt(6, bookingID.getTid().getTicketID());
            stm.executeUpdate();

            String sql2 = "UPDATE [dbo].[Ticket]\n"
                    + "   SET [buyer] = ?\n"
                    + " WHERE ticketID = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql2);
            stm1.setInt(1, bookingID.getCustomerID().getCustomerID());
            stm1.setInt(2, bookingID.getTid().getTicketID());
            stm1.executeUpdate();

            return true; // Booking successful

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Booking failed
        }
    }

}
