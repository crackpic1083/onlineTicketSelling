/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aircraft;
import model.Booking;
import model.Flight;
import model.Ticket;
import model.User;

/**
 *
 * @author Admin
 */
public class BookingDAO extends DBContext {

    //Khach hang xem gio hang cua khach hang
    public ArrayList<Booking> getCustomerCart(int customerID, int status) {
        ArrayList<Booking> bo = new ArrayList<>();
        try {
            String sql = "select f.flightID, f.flightName, a.aircraftID, a.aircraftName, t.ticketID, t.ticketName,\n"
                    + "f.arrivalCity, f.departureCity, f.arrivalTime,\n"
                    + "f.departureTime, t.price, t.seatName,\n"
                    + "b.bookingID, b.passengerName, b.passengerEmail, b.passengerPhone, b.status, b.bookingDate\n"
                    + "from dbo.Flight f\n"
                    + "inner join dbo.Aircraft a\n"
                    + "on f.aircraftID=a.aircraftID\n"
                    + "inner join dbo.Ticket t\n"
                    + "on f.flightID = t.flightID\n"
                    + "inner join dbo.Booking b\n"
                    + "on b.ticketID = t.ticketID\n"
                    + "inner join dbo.Customer c\n"
                    + "on c.customerID = b.customerID\n"
                    + "where c.customerID = ? and status = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, customerID);
            stm.setInt(2, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightID(rs.getInt("flightID"));
                f.setFlightName(rs.getString("flightName"));
                f.setArrivalCity(rs.getString("arrivalCity"));
                f.setDepartureCity(rs.getString("departureCity"));
                f.setArrivalTime(rs.getTimestamp("arrivalTime"));
                f.setDepartureTime(rs.getTimestamp("departureTime"));

                Aircraft a = new Aircraft();
                a.setAircraftID(rs.getInt("aircraftID"));
                a.setAircraftName(rs.getString("aircraftName"));

                Ticket t = new Ticket();
                t.setTicketID(rs.getInt("ticketID"));
                t.setTicketName(rs.getString("ticketName"));
                t.setPrice(rs.getFloat("price"));
                t.setSeatName(rs.getString("seatName"));

                Booking b = new Booking();
                b.setBookingID(rs.getInt("bookingID"));
                b.setName(rs.getString("passengerName"));
                b.setEmail(rs.getString("passengerEmail"));
                b.setPhone(rs.getString("passengerPhone"));
                b.setStatus(rs.getInt("status"));
                b.setBookingDate(rs.getTimestamp("bookingDate"));

                b.setFid(f);
                b.setAid(a);
                b.setTid(t);
                bo.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bo;
    }

    public void updateCartPending(Booking bid) {
        try {
            String sql = "UPDATE [dbo].[Booking]\n"
                    + "   SET [passengerPhone] = ?\n"
                    + "      ,[passengerName] = ?\n"
                    + "      ,[passengerEmail] = ?\n"
                    + "      ,[ticketID] = ?\n"
                    + " WHERE bookingID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bid.getPhone());
            stm.setString(2, bid.getName());
            stm.setString(3, bid.getEmail());
            stm.setInt(4, bid.getTid().getTicketID());
            stm.setInt(5, bid.getBookingID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    public Booking viewForUpdatePending(int bid) {
        try {
            String sql = "select bookingID, passengerName, passengerEmail, passengerPhone, ticketID from dbo.Booking \n"
                    + "where bookingID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("bookingID"));
                b.setName(rs.getString("passengerName"));
                b.setEmail(rs.getString("passengerEmail"));
                b.setPhone(rs.getString("passengerPhone"));
                
                Ticket t = new Ticket();
                t.setTicketID(rs.getInt("ticketID"));
                b.setTid(t);
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //@369f73a2
    
    public static void main(String[] args) {
        BookingDAO bDAO= new BookingDAO();
        Booking b = bDAO.viewForUpdatePending(10);
        System.out.println(b);
    }

    //In ra so do ghe ngoi de update
    public ArrayList<Ticket> getTicketForUpdatePending(int flightID, int buyer) {
        ArrayList<Ticket> ti = new ArrayList<>();
        try {
            String sql = "select f.flightID, t.ticketID, t.ticketName, t.buyer, t.seatName \n"
                    + "from dbo.Ticket t \n"
                    + "inner join Flight f on t.flightID=f.flightID\n"
                    + "where f.flightID=? and (t.buyer is null or t.buyer = ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flightID);
            stm.setInt(2, buyer);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightID(rs.getInt("flightID"));
                
                Ticket t = new Ticket();
                t.setTicketID(rs.getInt("ticketID"));
                t.setTicketName(rs.getString("ticketName"));
                t.setBuyer(rs.getInt("buyer"));
                t.setSeatName(rs.getString("seatName"));
                t.setFlightID(f);
                ti.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ti;
    }

}
