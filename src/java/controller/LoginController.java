

package controller;

import dal.AdminDAO;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Admin;
import model.User;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        
        CustomerDAO cDAO= new CustomerDAO();
        User user = cDAO.getUser(username, password);
        
        AdminDAO aDAO= new AdminDAO();
        Admin admin = aDAO.getAdmin(username, password);
        
        if(user!=null && admin==null){
            request.getSession().setAttribute("role", "Customer");
            request.getSession().setAttribute("account", user);
            response.sendRedirect("search");
        }
        else if(user==null && admin!=null){
            request.getSession().setAttribute("role", "Admin");
            request.getSession().setAttribute("account", admin);
            response.sendRedirect("checkcart");
        }
        else{
            request.setAttribute("notice", "Incorrect username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
