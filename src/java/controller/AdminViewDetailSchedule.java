/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.FlightDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Aircraft;
import model.Flight;
import model.Ticket;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminViewDetailSchedule", urlPatterns = {"/adminviewdetailschedule"})
public class AdminViewDetailSchedule extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") != null) {
            if (request.getSession().getAttribute("role").equals("Admin")) {
                FlightDAO fDAO = new FlightDAO();
                int flightID = Integer.parseInt(request.getParameter("f"));
                request.setAttribute("status", request.getParameter("status"));
                Flight f = fDAO.getAdminFlightDetail(flightID);
                ArrayList<Aircraft> ar = fDAO.getAircraft();
                request.setAttribute("ar", ar);
                request.setAttribute("f", f);
                int a = fDAO.getAvailableTicket(flightID);
                request.setAttribute("aticket", a);
                int b = fDAO.getBookedTicket(flightID);
                request.setAttribute("bticket", b);
                List<String> seats = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    seats.add(i + "A");
                    seats.add(i + "B");
                    seats.add(i + "C");
                    seats.add(i + "D");
                    seats.add(i + "E");
                    seats.add(i + "F");
                }
                request.setAttribute("seats", seats);
                String flightName = f.getFlightName().substring(2);
                ArrayList<Ticket> tickets = fDAO.getAvailableFlightTicket(flightID);
                ArrayList<Ticket> ti = fDAO.getBookedFlightTicket(flightID);
                request.setAttribute("ti", tickets);
                request.setAttribute("tickets", ti);
                request.setAttribute("flightName", flightName);
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                request.setAttribute("today", currentTimestamp);
                request.getRequestDispatcher("adminViewDetailFlightSchedule.jsp").forward(request, response);

            } else {
                response.sendRedirect("login");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession().getAttribute("account") != null) {
            if (request.getSession().getAttribute("role").equals("Admin")) {
                if (request.getParameter("mod").equals("update")) {

                    AdminDAO ad = new AdminDAO();
                    ArrayList<Aircraft> ar = ad.getAircraft();
                    request.setAttribute("arlist", ar);

                    List<String> seats = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        seats.add(i + "A");
                        seats.add(i + "B");
                        seats.add(i + "C");
                        seats.add(i + "D");
                        seats.add(i + "E");
                        seats.add(i + "F");
                    }
                    request.setAttribute("seats", seats);

                    String fname = request.getParameter("fname");
                    String mes6 = null;
                    if (Integer.parseInt(fname) <= 0) {
                        mes6 = "Flight Name have number greater than 0";
                    }
                    request.setAttribute("errorMessage6", mes6);
                    String flightName = request.getParameter("vn") + fname;
                    int aircraftID = Integer.parseInt(request.getParameter("arname"));
                    String arrivalCity = request.getParameter("arrcity");

                    String arrivalTime = request.getParameter("arrtime");
                    LocalDateTime localDateTime = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                    Timestamp arrTime = Timestamp.valueOf(localDateTime);

                    String departureCity = request.getParameter("decity");
                    String departureTime = request.getParameter("detime");
                    LocalDateTime localDateTime1 = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                    Timestamp deTime = Timestamp.valueOf(localDateTime1);

                    String mes2 = isDepartureMatch(arrivalCity, departureCity);
                    request.setAttribute("errorMessage2", mes2);

                    String mes3 = null;
                    if (deTime.compareTo(arrTime) < 1) {
                        mes3 = "Departure Time must be greater than Arrival Time";
                    }
                    request.setAttribute("errorMessage3", mes3);

                    String mes4 = null;
                    java.util.Date today = new java.util.Date();

                    java.util.Date to1 = DateTimeHelper.addDays(today, 15);
                    Timestamp from = DateTimeHelper.toTimestampSql(to1);
                    if (arrTime.compareTo(from) < 1) {
                        mes4 = "Arrival Time have to later than today at least 15 days";
                    }
                    request.setAttribute("errorMessage4", mes4);

                    String price = request.getParameter("price");

                    String mes7 = null;
                    if (Float.parseFloat(price) < 0.0) {
                        mes7 = "Price must be equal or greater than 0 ";
                    }
                    request.setAttribute("errorMessage7", mes7);
                    ArrayList<Ticket> tickets = new ArrayList<>();
                    for (String s : seats) {
                        String seat = request.getParameter("seatname" + s);
                        if (seat != null) {
                            Ticket t = new Ticket();
                            t.setPrice(Float.parseFloat(price));
                            t.setSeatName(seat);
                            t.setTicketName(flightName + "_" + seat);
                            tickets.add(t);
                        }

                    }
                    request.setAttribute("tickets", tickets);

                    String mes5 = null;
                    if (tickets.isEmpty()) {
                        mes5 = "Please choose seats for flight";
                    }
                    request.setAttribute("errorMessage5", mes5);
                    if (mes2 != null || mes3 != null || mes4 != null || mes5 != null || mes6 != null || mes7 != null) {
                        request.getRequestDispatcher("adminAddFlight.jsp").forward(request, response);
                    } else {
                        Flight f = new Flight();
                        f.setFlightID(Integer.parseInt(request.getParameter("flightid")));
                        f.setArrivalCity(arrivalCity);
                        f.setArrivalTime(arrTime);
                        f.setDepartureCity(departureCity);
                        f.setDepartureTime(deTime);

                        Aircraft ac = new Aircraft();
                        ac.setAircraftID(aircraftID);
                        f.setAircraftID(ac);

                        Admin admin = (Admin) request.getSession().getAttribute("account");
                        f.setUpdateBy(admin);

                        ad.updateFlight(f, tickets);
                        response.sendRedirect("adminviewschedule");

                    }

                } else {
                    AdminDAO ad = new AdminDAO();
                    int flightid = Integer.parseInt(request.getParameter("flightid"));
                    ad.deleteFlight(flightid);
                    response.sendRedirect("adminviewschedule");

                }

            } else {
                response.sendRedirect("login");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String isValidFlightName(String flightName) {
        String mes1 = null;
        AdminDAO ad = new AdminDAO();
        String check = ad.isValidFlightName(flightName);
        if (flightName.equals(check)) {
            mes1 = "Flight name already exist";
        }
        return mes1;
    }

    public String isDepartureMatch(String arcity, String decity) {
        String mes2 = null;
        if (decity.equals(arcity)) {
            mes2 = "City can not be match";
        }
        return mes2;

    }
}

//Lamf lai bai employee, departure, student
//front end 
//lam update flight detail kduoc update id, createBy, createDate, updateBy, upgradeDate, 
//cho select chonj aircraft, departure city, arrival city cho chonj nhu register
//trong model flight thieeus thuoc tinh numOdSeat cua chuyen bay, thieu tong so ghe cua chuyen bay, so ve da dat

