/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Admin;
import model.User;

/**
 *
 * @author Admin
 */
public class RegistrationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registrationForm.jsp").forward(request, response);

        //Validate Data
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //CUSTOMER
        response.setContentType("text/html;charset=UTF-8");
        String raw_name = request.getParameter("name");
        request.setAttribute("raw_name", raw_name);
        String raw_email = request.getParameter("email");
        request.setAttribute("raw_email", raw_email);
        String raw_phone = request.getParameter("phone");
        request.setAttribute("raw_phone", raw_phone);
        Date raw_dob = Date.valueOf(request.getParameter("dob"));
        request.setAttribute("raw_dob", raw_dob);
        boolean raw_gender = (request.getParameter("gender")).equals("male");
        request.setAttribute("raw_gender", request.getParameter("gender"));
        String ward = request.getParameter("ward");
        request.setAttribute("ward", ward);
        String district = request.getParameter("district");
        request.setAttribute("district", district);
        String city = request.getParameter("city");
        request.setAttribute("city", city);
        String raw_cccd = request.getParameter("cccd");
        request.setAttribute("raw_cccd", raw_cccd);
        String raw_username = request.getParameter("username");
        request.setAttribute("raw_username", raw_username);
        String raw_password = request.getParameter("password");
        request.setAttribute("raw_password", raw_password);
        String raw_rpassword = request.getParameter("rpassword");
        request.setAttribute("raw_rpassword", raw_rpassword);

        //Validate
        String mes1 = isValidFullName(raw_name);
        request.setAttribute("errorMessage1", mes1);
        String mes2 = isValidEmail(raw_email);
        request.setAttribute("errorMessage2", mes2);
        String mes3 = isValidPhone(raw_phone);
        request.setAttribute("errorMessage3", mes3);
        String mes4 = isOver18YearsOld(raw_dob);
        request.setAttribute("errorMessage4", mes4);
        String mes5 = IsValidAddress(ward, district, city);
        request.setAttribute("errorMessage5", mes5);
        String mes6 = isValidCccd(raw_cccd);
        request.setAttribute("errorMessage6", mes6);
        String mes7 = isValidUsername(raw_username);
        request.setAttribute("errorMessage7", mes7);
        String mes8 = isStrongPassword(raw_password);
        request.setAttribute("errorMessage8", mes8);
        String mes9 = isPasswordMatch(raw_password, raw_rpassword);
        request.setAttribute("errorMessage9", mes9);
        

        if (mes1 != null || mes2 != null || mes3 != null || mes4 != null || mes5 != null || mes6 != null || mes7 != null || mes8 != null || mes9 != null) {
            request.getRequestDispatcher("registrationForm.jsp").forward(request, response);
        } else {
            User u = new User();
            CustomerDAO cDAO = new CustomerDAO();
            u.setCustomerUsername(raw_username);
            u.setCustomerPassword(raw_rpassword);
            u.setCustomerName(raw_name);
            u.setCustomerPhone(raw_phone);
            u.setCustomerCCCD(raw_cccd);
            u.setCustomerGender(raw_gender);
            u.setCustomerDOB(raw_dob);
            u.setCustomerEmail(raw_email);
            u.setCustomerAddress(ward + ", " + district + ", " + city);
            cDAO.addUser(u);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    //Check full name 
    public String isValidFullName(String fullName) {
        String mes1 = null;
        String regex = "^[\\p{L}\\s]+$";
        if (!fullName.matches(regex) || fullName == null) {
            mes1 = "Invalid full name";
        }
        return mes1;
    }

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

    //Check DOB
    public String isOver18YearsOld(Date dob) {
        String mes4 = null;
        if (dob == null || dob.equals("")) {
            mes4 = "Date of birth must not be empty";

        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < cal.get(Calendar.MONTH)
                || (now.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        if (age < 18) {
            mes4 = "Must be greater than 18 years old";

        }

        return mes4;

    }

    //Check address
    public String IsValidAddress(String city, String district, String ward) {
        String mes5 = null;
        if (city == null || district == null || ward == null) {
            mes5 = "Invalid address";
        }
        return mes5;
    }

    //Check cccd
    public String isValidCccd(String citizenID) {
        String mes6 = null;
        String regex = "^[0-9]{12}$";
        if (!citizenID.matches(regex) || citizenID == null) {
            mes6 = "Invalid citizen ID number";
        }
        return mes6;
    }

    public String isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        String mes7 = null;
        CustomerDAO cDAO = new CustomerDAO();
        AdminDAO aDAO = new AdminDAO();
        String check = cDAO.isValidUsername(username);
        String check1 = aDAO.isValidUsername(username);
        if (username.equals(check) || username.equals(check1)) {
            mes7 = "Username already exists";
        }
        if (!username.matches(regex) || username == null) {
            mes7 = "Invalid username";
        }
        return mes7;
    }

    //Check password >= 8 ki tu
    public String isStrongPassword(String password) {
        String mes8 = null;
        String regex = ".{9,}";
        if (!password.matches(regex) || password == null) {
            mes8 = "Password must be longer than 8 characters";
        }
        return mes8;
    }

    //confirm password
    public String isPasswordMatch(String password, String confirmPassword) {
        String mes9 = null;
        if (!confirmPassword.equals(password)) {
            mes9 = "Password must be match";
        }
        return mes9;
    }

}
