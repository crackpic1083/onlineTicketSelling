/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Booking;
import model.User;

/**
 *
 * @author Admin
 */
public class AdminCheckCart extends HttpServlet {

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
            out.println("<title>Servlet AdminCheckCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminCheckCart at " + request.getContextPath() + "</h1>");
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
                request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
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
            if (request.getSession().getAttribute("role").equals("Admin")) {

                AdminDAO ad = new AdminDAO();
                int mod = Integer.parseInt(request.getParameter("mod"));

                if (mod == 1) {
                    ArrayList<Booking> a = ad.getCustomerCart(1);
                    request.setAttribute("checkcart", a);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
                }

                if (mod == 2) {
                    ArrayList<Booking> a = ad.getCustomerCart(2);
                    request.setAttribute("checkcart", a);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
                }

                if (mod == 3) {
                    ArrayList<Booking> a = ad.getCustomerCart(3);
                    request.setAttribute("checkcart", a);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
                }

                if (mod == 4) {
                    ArrayList<Booking> a = ad.getCustomerCart(4);
                    request.setAttribute("checkcart", a);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
                }
                //cf
                int id = Integer.parseInt(request.getParameter("id"));
                int ticketid = Integer.parseInt(request.getParameter("ticketid"));
                if (mod == 5) {
                    ad.confirmFunctionAdmin(id, 2);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);

                }
                //reject
                if (mod == 6) {
                    ad.cartFunction(4, id, ticketid);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
                }
                //Delete
                if (mod == 7) {
                    ad.deleteFunctionAdmin(id);
                    request.getRequestDispatcher("adminCheckCustomerCart.jsp").forward(request, response);
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

}
