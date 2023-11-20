/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Flight;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class AdminViewSchedule extends HttpServlet {

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
        if (request.getSession().getAttribute("account") != null) {
            if (request.getSession().getAttribute("role").equals("Admin")) {
                AdminDAO aDAO = new AdminDAO();
                Timestamp from = null;
                Timestamp to = null;
                if (request.getParameter("fromdate") == null) {
                    Date today = new Date();
                    Date to1 = DateTimeHelper.addDays(today, 6);
                    from = DateTimeHelper.toTimestampSql(today);//Chuyen ngay giua javautil voi javasql
                    to = DateTimeHelper.toTimestampSql(to1);
                }
                if (request.getParameter("fromdate") != null) {
                    LocalDate date = LocalDate.parse(request.getParameter("fromdate"));
                    from = Timestamp.valueOf(date.atStartOfDay());
                    Date from1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to2 = DateTimeHelper.addDays(from1, 6);
                    to = DateTimeHelper.toTimestampSql(to2);
                }
                ArrayList<java.sql.Timestamp> dates = DateTimeHelper.getDatesList(from, to);
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                request.setAttribute("today", currentTimestamp);
                request.setAttribute("from", new java.sql.Date(DateTimeHelper.toDateUtil(from).getTime()));
                request.setAttribute("dates", dates);
                ArrayList<Flight> flight = aDAO.getAdminFlightSchedule();
                request.setAttribute("flight", flight);
                request.getRequestDispatcher("adminViewFlightSchedule.jsp").forward(request, response);

            } else {
                response.sendRedirect("login");

            }
        } else {
            response.sendRedirect("login");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
