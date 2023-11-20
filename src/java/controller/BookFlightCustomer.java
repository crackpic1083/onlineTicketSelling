/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.FlightDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.Ticket;
import model.User;

/**
 *
 * @author Admin
 */
public class BookFlightCustomer extends HttpServlet {

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
            out.println("<title>Servlet BookFlightCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookFlightCustomer at " + request.getContextPath() + "</h1>");
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
            if (request.getSession().getAttribute("role").equals("Customer")) {
                FlightDAO db = new FlightDAO();
                ArrayList<Ticket> ti = db.getFlightTicket(Integer.parseInt(request.getParameter("f")));
                request.setAttribute("ti", ti);
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
                request.getRequestDispatcher("bookingDetail.jsp").forward(request, response);
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
        if (request.getSession().getAttribute("account") != null) {
            if (request.getSession().getAttribute("role").equals("Customer")) {
                String name = request.getParameter("name");
                request.setAttribute("name", name);
                String email = request.getParameter("email");
                request.setAttribute("email", email);
                String phone = request.getParameter("phone");
                request.setAttribute("phone", phone);
                int seat = Integer.parseInt(request.getParameter("seat"));
                String mes1 = isValidEmail(email);
                request.setAttribute("errorMessage1", mes1);
                String mes2 = isValidPhone(phone);
                request.setAttribute("errorMessage2", mes2);
                String mes3 = null;
                
                if (mes1 != null || mes2 != null || mes3 != null) {
                    request.getRequestDispatcher("bookingDetail.jsp").forward(request, response);
                } else {

                    User u = (User) request.getSession().getAttribute("account");
                    CustomerDAO db = new CustomerDAO();
                    Booking bo = new Booking();
                    bo.setCustomerID(u);
                    bo.setEmail(email);
                    bo.setName(name);
                    bo.setPhone(phone);
                    bo.setStatus(1);

                    Ticket t = new Ticket();
                    t.setTicketID(seat);
                    bo.setTid(t);
                    db.bookTicket(bo);
                    boolean isTicketBooked = db.bookTicket(bo);
                    if (!isTicketBooked) {
                        mes3 = "Please choose a seat";
                    }
                    request.setAttribute("errorMessage3", mes3);
                    response.sendRedirect("search");
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

    //Check email
    public String isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String mes2 = null;
        if (!email.matches(regex) || email == null) {
            mes2 = "Invalid email";
        }
        return mes2;
    }

    //Check so dien thoai
    public String isValidPhone(String phoneNumber) {
        String regex = "^0\\d{9}$";
        String mes3 = null;
        if (!phoneNumber.matches(regex) || phoneNumber == null) {
            mes3 = "Invalid phone number";
        }
        return mes3;
    }
}
