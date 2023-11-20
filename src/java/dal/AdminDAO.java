/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class AdminDAO extends DBContext {

    public Admin getAdmin(String username, String password) {
        try {
            String sql = "select * from dbo.Admin where adminUsername=? and adminPassword=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminID(rs.getInt(1));
                admin.setAdminUsername(rs.getString(2));
                admin.setAdminPassword(rs.getString(3));
                admin.setAdminName(rs.getString(4));
                admin.setAdminEmail(rs.getString(5));
                admin.setAdminPhone(rs.getString(6));
                admin.setAdminGender(rs.getBoolean(7));
                admin.setAdminDOB(rs.getDate(8));
                admin.setAdminCCCD(rs.getString(9));
                admin.setAdminAddress(rs.getString(10));
                return admin;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Flight> getAdminFlightSchedule() {
        ArrayList<Flight> flight = new ArrayList<Flight>();
        try {
            String sql = "Select flightID, flightName, arrivalCity, departureCity, arrivalTime, departureTime \n"
                    + "From dbo.Flight\n"
                    + "order by arrivalTime asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            //while tra ra kq nhieu hon mot dong dung khi doi tuong la ArrayList
            //If tra ra kq 1 dong khi tra ve 1 doi tuong
            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightID(rs.getInt("flightID"));
                f.setFlightName(rs.getString("flightName"));
                f.setArrivalCity(rs.getString("arrivalCity"));
                f.setDepartureCity(rs.getString("departureCity"));
                f.setArrivalTime(rs.getTimestamp("arrivalTime"));
                f.setDepartureTime(rs.getTimestamp("departureTime"));
                flight.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flight;

    }

    public ArrayList<Admin> getAdminViewFlightDetail() {
        ArrayList<Admin> admin = new ArrayList<>();
        String sql = "SELECT f.flightID, f.flightName, ad_create.adminID AS AdminID_Create, ad_create.adminName AS AdminName_Create, \n"
                + "    f.createDate, ad_update.adminID AS AdmiID_Update, ad_update.adminName AS AdminName_Update, f.upgradeDate, \n"
                + "    a.aircraftID , a.aircraftName, f.arrivalCity, f.arrivalTime, f.departureCity, f.departureTime\n"
                + "FROM dbo.Flight f\n"
                + "INNER JOIN dbo.Admin ad_create ON ad_create.adminID = f.createBy\n"
                + "INNER JOIN dbo.Admin ad_update ON ad_update.adminID = f.upgrateBy\n"
                + "INNER JOIN dbo.Aircraft a ON f.aircraftID = a.aircraftID\n"
                + "Where f.flightID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Admin adminfd = new Admin();
                adminfd.setAdminID(rs.getInt(1));
                adminfd.setAdminUsername(rs.getString(2));
                adminfd.setAdminPassword(rs.getString(3));
                adminfd.setAdminName(rs.getString(4));
                adminfd.setAdminEmail(rs.getString(5));
                adminfd.setAdminPhone(rs.getString(6));
                adminfd.setAdminGender(rs.getBoolean(7));
                adminfd.setAdminDOB(rs.getDate(8));
                adminfd.setAdminCCCD(rs.getString(9));
                adminfd.setAdminAddress(rs.getString(10));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String isValidUsername(String username) {
        try {
            String strSQL = "select adminUsername from dbo.Admin where adminUsername=?";
            PreparedStatement stm = connection.prepareStatement(strSQL);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Booking> getCustomerCart(int status) {
        ArrayList<Booking> bo = new ArrayList<>();
        try {
            String sql = "select f.flightID, f.flightName, a.aircraftID, a.aircraftName, t.ticketID, t.ticketName,\n"
                    + "f.arrivalCity, f.departureCity, f.arrivalTime,\n"
                    + "f.departureTime, t.price, t.seatName, c.customerID, c.customerName,\n"
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
                    + "where b.status=? \n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
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

                User u = new User();
                u.setCustomerID(rs.getInt("customerID"));
                u.setCustomerName(rs.getString("customerName"));

                b.setFid(f);
                b.setAid(a);
                b.setTid(t);
                b.setCustomerID(u);
                bo.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bo;
    }

    //confirm, 
    public void confirmFunctionAdmin(int id, int status) {
        try {
            String sql = "UPDATE [dbo].[Booking]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE bookingID= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //reject, cancel
    public void cartFunction(int status, int id, int ticketid) {
        try {
            String sql = "UPDATE [dbo].[Booking]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE bookingID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
            String sql1 = "UPDATE [dbo].[Ticket]\n"
                    + "   SET [buyer] = NULL\n"
                    + " WHERE ticketID = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setInt(1, ticketid);
            stm1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Delete
    public void deleteFunctionAdmin(int bookingid) {
        try {
            String sql = "DELETE FROM [dbo].[Booking]\n"
                    + "      WHERE bookingID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, bookingid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Add flight admin
    public void addNewFlight(Flight f, ArrayList<Ticket> tickets) {
        try {
            String sql = "INSERT INTO [dbo].[Flight]\n"
                    + "           ([flightName]\n"
                    + "           ,[createBy]\n"
                    + "           ,[upgrateBy]\n"
                    + "           ,[aircraftID]\n"
                    + "           ,[arrivalCity]\n"
                    + "           ,[arrivalTime]\n"
                    + "           ,[departureCity]\n"
                    + "           ,[departureTime])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, f.getFlightName());
            stm.setInt(2, f.getCreateBy().getAdminID());
            stm.setInt(3, f.getUpdateBy().getAdminID());
            stm.setInt(4, f.getAircraftID().getAircraftID());
            stm.setString(5, f.getArrivalCity());
            stm.setTimestamp(6, f.getArrivalTime());
            stm.setString(7, f.getDepartureCity());
            stm.setTimestamp(8, f.getDepartureTime());
            int i = stm.executeUpdate();
            if (i > 0) {
                String sql1 = "SELECT TOP (1) flightID, flightName\n"
                        + "FROM dbo.Flight\n"
                        + "ORDER BY createDate desc;";
                PreparedStatement stm1 = connection.prepareStatement(sql1);
                ResultSet rs = stm1.executeQuery();
                if (rs.next()) {
                    f.setFlightID(rs.getInt("flightID"));
                    for (Ticket ticket : tickets) {
                        String sql2 = "INSERT INTO [dbo].[Ticket]\n"
                                + "           ([ticketName]\n"
                                + "           ,[price]\n"
                                + "           ,[flightID]\n"
                                + "           ,[seatName])\n"
                                + "     VALUES\n"
                                + "           (?,?,?,?)";
                        PreparedStatement stm2 = connection.prepareStatement(sql2);
                        stm2.setString(1, ticket.getTicketName());
                        stm2.setFloat(2, ticket.getPrice());
                        stm2.setInt(3, f.getFlightID());
                        stm2.setString(4, ticket.getSeatName());
                        stm2.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFlight(Flight f, ArrayList<Ticket> tickets) {
        try {
            String sql = "UPDATE [dbo].[Flight]\n"
                    + "   SET [upgrateBy] = ?    \n"
                    + "      ,[aircraftID] = ?\n"
                    + "      ,[arrivalCity] = ?\n"
                    + "      ,[arrivalTime] = ?\n"
                    + "      ,[departureCity] = ?\n"
                    + "      ,[departureTime] = ?\n"
                    + " WHERE [flightID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, f.getUpdateBy().getAdminID());
            stm.setInt(2, f.getAircraftID().getAircraftID());
            stm.setString(3, f.getArrivalCity());
            stm.setTimestamp(4, f.getArrivalTime());
            stm.setString(5, f.getDepartureCity());
            stm.setTimestamp(6, f.getDepartureTime());
            stm.setInt(7, f.getFlightID());
            stm.executeUpdate();
            String sql1 = "DELETE FROM [dbo].[Ticket]\n"
                    + "      WHERE [flightID] = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setInt(1, f.getFlightID());
            stm1.executeUpdate();

            for (Ticket ticket : tickets) {
                String sql2 = "INSERT INTO [dbo].[Ticket]\n"
                        + "           ([ticketName]\n"
                        + "           ,[price]\n"
                        + "           ,[flightID]\n"
                        + "           ,[seatName])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?)";
                PreparedStatement stm2 = connection.prepareStatement(sql2);
                stm2.setString(1, ticket.getTicketName());
                stm2.setFloat(2, ticket.getPrice());
                stm2.setInt(3, f.getFlightID());
                stm2.setString(4, ticket.getSeatName());
                stm2.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                a.setNumOfSeat(rs.getInt("numOFSeat"));
                ar.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }

    public String isValidFlightName(String flightName) {
        try {
            String strSQL = "select flightName from dbo.Flight\n"
                    + "where flightName=?";
            PreparedStatement stm = connection.prepareStatement(strSQL);
            stm.setString(1, flightName);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public static void main(String[] args) {
//        AdminDAO ad = new AdminDAO();
//        
//        Flight f = new Flight();
//        
//        Admin a = new Admin();
//        a.setAdminID(3);         
//        f.setUpdateBy(a);
//        
//        
//        Aircraft ac = new Aircraft();        
//        ac.setAircraftID(9);
//        f.setAircraftID(ac);
//        
//        f.setFlightID(1013);
//        f.setArrivalCity("Tỉnh Thừa Thiên Huế");
//        f.setArrivalTime(Timestamp.valueOf("2023-08-25 09:00:00.0"));
//        f.setDepartureCity("Tỉnh Cà Mau");
//        f.setDepartureTime(Timestamp.valueOf("2023-08-25 10:30:00.0"));
//        
//        Ticket t1 = new Ticket();
//        t1.setTicketName("VN1008_10F");
//        t1.setPrice(14000);
//        t1.setSeatName("10F");
//
//        Ticket t2 = new Ticket();
//        t2.setTicketName("VN1008_9F");
//        t2.setPrice(14000);     
//        t2.setSeatName("9F");
//
//        ArrayList<Ticket> tickets = new ArrayList<>();
//        tickets.add(t1);
//        tickets.add(t2);
//
//        ad.updateFlight(f,tickets);
//        
//    }
    public void deleteFlight(int flightid) {

        try {
            String sql1 = "DELETE FROM [dbo].[Ticket]\n"
                    + "      WHERE [flightID] = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setInt(1, flightid);
            stm1.executeUpdate();

            String sql2 = "DELETE FROM [dbo].[Flight]\n"
                    + "      WHERE [flightID] = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setInt(1, flightid);
            stm2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //vote, cmt de thi
    //gui thong bao ve bat ki thay doi nao voi de th co da set lich kiem tra tu truoc 
}
