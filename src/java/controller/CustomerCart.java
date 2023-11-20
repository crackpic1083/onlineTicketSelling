/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.BookingDAO;
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
import model.Flight;
import model.Ticket;
import model.User;

/**
 *
 * @author Admin
 */
public class CustomerCart extends HttpServlet {

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
            out.println("<title>Servlet CustomerCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerCart at " + request.getContextPath() + "</h1>");
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
                request.getRequestDispatcher("customerCart.jsp").forward(request, response);
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
                //In ra gio hang cua khach hang 
                BookingDAO bo = new BookingDAO();
                User u = (User) request.getSession().getAttribute("account");
                int customerID = u.getCustomerID();
                int mod = Integer.parseInt(request.getParameter("mod"));
                if (mod == 1) {
                    ArrayList<Booking> b = bo.getCustomerCart(customerID, 1);
                    request.setAttribute("cart", b);
                    request.getRequestDispatcher("customerCart.jsp").forward(request, response);
                }
                if (mod == 2) {
                    ArrayList<Booking> b = bo.getCustomerCart(customerID, 2);
                    request.setAttribute("cart", b);
                    request.getRequestDispatcher("customerCart.jsp").forward(request, response);
                }
                if (mod == 3) {
                    ArrayList<Booking> b = bo.getCustomerCart(customerID, 3);
                    request.setAttribute("cart", b);
                    request.getRequestDispatcher("customerCart.jsp").forward(request, response);
                }
                if (mod == 4) {
                    ArrayList<Booking> b = bo.getCustomerCart(customerID, 4);
                    request.setAttribute("cart", b);
                    request.getRequestDispatcher("customerCart.jsp").forward(request, response);
                }

                if (mod == 5) {
                    int bid = Integer.parseInt(request.getParameter("id"));
                    Booking b = bo.viewForUpdatePending(bid);
                    request.setAttribute("view", b);
                    List<String> seats = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        seats.add(i + "A");
                        seats.add(i + "B");
                        seats.add(i + "C");
                        seats.add(i + "D");
                        seats.add(i + "E");
                        seats.add(i + "F");
                    }
                    int fid = Integer.parseInt(request.getParameter("fid"));
                    request.setAttribute("seats", seats);
                    int buyer = u.getCustomerID();
                    ArrayList<Ticket> ti = bo.getTicketForUpdatePending(fid, buyer);
                    request.setAttribute("ti", ti);
                    request.getRequestDispatcher("viewForUpdatePending.jsp").forward(request, response);
                }

                if (mod == 6) {
                    AdminDAO ad = new AdminDAO();
                    int bid = Integer.parseInt(request.getParameter("id"));
                    int tid = Integer.parseInt(request.getParameter("tid"));
                    ad.cartFunction(3, bid, tid);
                    request.getRequestDispatcher("customerCart.jsp").forward(request, response);
                }

                if (mod == 7) {
                    String name = request.getParameter("name");
                    request.setAttribute("name", name);
                    String email = request.getParameter("email");
                    request.setAttribute("email", email);
                    String phone = request.getParameter("phone");
                    request.setAttribute("phone", phone);
                    String mes1 = isValidEmail(email);
                    request.setAttribute("errorMessage1", mes1);
                    String mes2 = isValidPhone(phone);
                    request.setAttribute("errorMessage2", mes2);
                    if (mes1 != null || mes2 != null) {
                        request.getRequestDispatcher("viewForUpdatePending.jsp").forward(request, response);
                    } else {
                        int seat = Integer.parseInt(request.getParameter("seat"));
                        int bid = Integer.parseInt(request.getParameter("id"));
                        Booking b = new Booking();
                        Ticket t = new Ticket();
                        b.setName(name);
                        b.setPhone(phone);
                        b.setEmail(email);
                        t.setTicketID(seat);
                        b.setTid(t);
                        b.setBookingID(bid);
                        bo.updateCartPending(b);
                        response.sendRedirect("cart");
                    }
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
