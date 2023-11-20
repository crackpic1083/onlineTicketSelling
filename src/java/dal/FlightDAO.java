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
import model.Admin;
import model.Aircraft;
import model.Booking;
import model.Flight;
import model.Ticket;
import model.User;

/**
 *
 * @author Admin
 */
public class FlightDAO extends DBContext {

    public Flight getAdminFlightDetail(int id) {
        String sql = "SELECT f.flightID, f.flightName, ad_create.adminID AS AdminID_Create, ad_create.adminName AS AdminName_Create,\n"
                + "f.createDate, ad_update.adminID AS AdmiID_Update, ad_update.adminName AS AdminName_Update, f.upgradeDate,\n"
                + "a.aircraftID , a.aircraftName, a.numOfSeat, f.arrivalCity, f.arrivalTime, f.departureCity, f.departureTime, t.price\n"
                + "FROM dbo.Flight f\n"
                + "INNER JOIN dbo.Admin ad_create ON ad_create.adminID = f.createBy\n"
                + "INNER JOIN dbo.Admin ad_update ON ad_update.adminID = f.upgrateBy\n"
                + "INNER JOIN dbo.Aircraft a ON f.aircraftID = a.aircraftID\n"
                + "LEFT JOIN dbo.Ticket t ON f.flightID = t.flightID\n"
                + "Where f.flightID = ?\n"
                + "GROUP BY f.flightID, f.flightName, ad_create.adminID, ad_create.adminName,\n"
                + "f.createDate, ad_update.adminID, ad_update.adminName, f.upgradeDate,\n"
                + "a.aircraftID , a.aircraftName, a.numOfSeat, f.arrivalCity, f.arrivalTime, f.departureCity, f.departureTime, t.price\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Flight f = new Flight();
                Admin ad1 = new Admin();
                Admin ad2 = new Admin();
                Aircraft a = new Aircraft();
                f.setFlightID(rs.getInt(1));
                f.setFlightName(rs.getString(2));
                ad1.setAdminID(rs.getInt(3));
                ad1.setAdminName(rs.getString(4));
                f.setCreateDate(rs.getTimestamp(5));
                ad2.setAdminID(rs.getInt(6));
                ad2.setAdminName(rs.getString(7));
                f.setUpgradeDate(rs.getTimestamp(8));
                a.setAircraftID(rs.getInt(9));
                a.setAircraftName(rs.getString(10));
                a.setNumOfSeat(rs.getInt(11));
                f.setArrivalCity(rs.getString(12));
                f.setArrivalTime(rs.getTimestamp(13));
                f.setDepartureCity(rs.getString(14));
                f.setDepartureTime(rs.getTimestamp(15));
                f.setPrice(rs.getFloat(16));
                f.setCreateBy(ad1);
                f.setUpdateBy(ad2);
                f.setAircraftID(a);
                return f;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getAvailableTicket(int id) {
        try {
            String sql = "select COUNT(*) from dbo.Ticket\n"
                    + "where flightID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getBookedTicket(int id) {
        try {
            String sql = "select COUNT(*) from dbo.Ticket\n"
                    + "where flightID=? and buyer is not null";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Aircraft> getAircraft() {
        ArrayList<Aircraft> ar = new ArrayList<>();
        try {
            String sql = "select * from dbo.Aircraft";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Aircraft a = new Aircraft();
                a.setAircraftID(rs.getInt("aircraftID"));
                a.setAircraftName(rs.getString("aircraftName"));
                a.setNumOfSeat(rs.getInt("numOfSeat"));
                ar.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }

    public ArrayList<Flight> searchFlightCustomer(String arrivalCity, String departureCity) {
        ArrayList<Flight> fl = new ArrayList<>();
        try {
            String sql = "Select f.flightID, f.flightName, f.arrivalCity, f.arrivalTime,f.departureCity,f.departureTime,t.price,a.aircraftID, a.aircraftName, count(*) as availableTicket from dbo.Flight f "
                    + "inner join dbo.Ticket t on t.flightID=f.flightID \n"
                    + "inner join dbo.Aircraft a on a.aircraftID = f.aircraftID\n"
                    + "where f.arrivalCity = ? and f.departureCity = ? and t.buyer is null\n"
                    + "group by f.flightID, f.flightName, f.arrivalCity, f.arrivalTime,f.departureCity,f.departureTime,t.price,a.aircraftID, a.aircraftName";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, arrivalCity);
            stm.setString(2, departureCity);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightID(rs.getInt("flightID"));
                f.setFlightName(rs.getString("flightName"));
                f.setArrivalCity(rs.getString("arrivalCity"));
                f.setArrivalTime(rs.getTimestamp("arrivalTime"));
                f.setDepartureCity(rs.getString("departureCity"));
                f.setDepartureTime(rs.getTimestamp("departureTime"));

                Aircraft ar = new Aircraft();
                f.setPrice(rs.getFloat("price"));
                ar.setAircraftID(rs.getInt("aircraftID"));
                ar.setAircraftName(rs.getString("aircraftName"));
                f.setAircraftID(ar);
                f.setAvailableTicket(rs.getInt("availableTicket"));
                fl.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fl;
    }

    public ArrayList<Ticket> getFlightTicket(int flightID) {
        ArrayList<Ticket> ti = new ArrayList<>();
        try {
            String sql = "select f.flightID, t.ticketID, t.ticketName, t.buyer, t.seatName from dbo.Ticket t \n"
                    + "inner join Flight f \n"
                    + "on t.flightID=f.flightID\n"
                    + "where f.flightID=? and t.buyer is null";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flightID);
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

    public ArrayList<Ticket> getAvailableFlightTicket(int flightID) {
        ArrayList<Ticket> ti = new ArrayList<>();
        try {
            String sql = "select f.flightID, t.ticketID, t.ticketName, t.buyer, t.seatName from dbo.Ticket t \n"
                    + "inner join Flight f \n"
                    + "on t.flightID=f.flightID\n"
                    + "where f.flightID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flightID);
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
    
    public ArrayList<Ticket> getBookedFlightTicket(int flightID) {
        ArrayList<Ticket> ti = new ArrayList<>();
        try {
            String sql = "select f.flightID, t.ticketID, t.ticketName, t.buyer, t.seatName from dbo.Ticket t \n"
                    + "inner join Flight f \n"
                    + "on t.flightID=f.flightID\n"
                    + "where f.flightID=? and t.buyer is not null";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flightID);
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

    public static void main(String[] args) {
        FlightDAO f = new FlightDAO();
        ArrayList<Ticket> fe = f.getFlightTicket(3);
        System.out.println(new FlightDAO().getAdminFlightDetail(22));
    }

}
