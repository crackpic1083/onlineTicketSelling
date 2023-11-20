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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
public class AdminAddFlight extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminAddFlight</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddFlight at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
                request.getRequestDispatcher("adminAddFlight.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");

            }
        } else {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession().getAttribute("account") != null) {
            if (request.getSession().getAttribute("role").equals("Admin")) {
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

                String mes1 = isValidFlightName(flightName);
                request.setAttribute("errorMessage1", mes1);

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
                request.setAttribute("fname", fname);
                request.setAttribute("arname", aircraftID);
                request.setAttribute("arrcity", arrivalCity);
                request.setAttribute("arrtime", arrTime);
                request.setAttribute("decity", departureCity);
                request.setAttribute("detime", deTime);

                String price = request.getParameter("price");
                request.setAttribute("price", price);
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
                if (mes1 != null || mes2 != null || mes3 != null || mes4 != null || mes5 != null || mes6 != null || mes7 != null) {
                    request.getRequestDispatcher("adminAddFlight.jsp").forward(request, response);
                } else {
                    Flight f = new Flight();
                    f.setFlightName(flightName);
                    f.setArrivalCity(arrivalCity);
                    f.setArrivalTime(arrTime);
                    f.setDepartureCity(departureCity);
                    f.setDepartureTime(deTime);

                    Aircraft ac = new Aircraft();
                    ac.setAircraftID(aircraftID);
                    f.setAircraftID(ac);

                    Admin admin = (Admin) request.getSession().getAttribute("account");
                    f.setCreateBy(admin);
                    f.setUpdateBy(admin);

                    ad.addNewFlight(f, tickets);
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
